def args = [:]
args.CLUSTER_NAME = ""
args.PROJECT_NAME = "springboot-workshop-feb-2021"
args.SERVICE_NAME = "customer-service"
args.SERVICE_VERSION = "0.0.1-SNAPSHOT"


node () {
        properties([disableConcurrentBuilds()])
        stage ('Code Checkout')
        {
            checkout scm
        }
        stage ('Build')
        {
            echo "Checkout completed. Starting the build"
            withMaven(maven: 'maven-latest') {
                sh 'mvn clean install package'
                //stash name:"jar", includes:"target/customer-service-*.jar"
            }
        }
        stage('unit tests') {
            withMaven(maven: 'maven-latest') {
                sh 'mvn test'   
            }
        }
        stage('Create Image Builder') {
            if (
                expression {
                openshift.withCluster() {
                  openshift.withProject(args.PROJECT_NAME) {
                      return !openshift.selector("bc", "${args.SERVICE_NAME}").exists()
                      }
                     }
                    }
                )
                script {
                    openshift.withCluster() {
                        openshift.withProject(args.PROJECT_NAME) {
                            openshift.newApp "registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~./", "--name=${args.SERVICE_NAME}"
                        }
                    }
                }
            }
        stage('Build Image') {
              script {
                openshift.withCluster() {
                  openshift.withProject(args.PROJECT_NAME) {
                      def build = openshift.selector("bc", "${args.SERVICE_NAME}");
                          def startedBuild = build.startBuild("--from-file=\"./target/${args.SERVICE_NAME}-${args.SERVICE_VERSION}.jar\"");
                      startedBuild.logs('-f');
                      echo "Customer service build status: ${startedBuild.object().status}";
                  }
                }
              }
          }
        stage('Tag Image') {
            script {
                openshift.withCluster() {
                    openshift.withProject(args.PROJECT_NAME) {
                        openshift.tag("${args.SERVICE_NAME}"+":latest", "${args.SERVICE_NAME}"+":dev")
                    }
                }
            }
        }
        stage('Deploy STAGE') {
              script {
                openshift.withCluster() {
                  openshift.withProject(args.PROJECT_NAME) {
                    if (openshift.selector('dc', "${args.SERVICE_NAME}").exists()) {
                      openshift.selector('dc', "${args.SERVICE_NAME}").delete()
                      openshift.selector('svc', "${args.SERVICE_NAME}").delete()
                      //openshift.selector('route', "${args.SERVICE_NAME}").delete()
                    }

                    openshift.newApp("${args.SERVICE_NAME}").narrow("svc").expose()
                    }
              }
            }
          }
}
