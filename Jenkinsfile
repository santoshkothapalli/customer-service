#!groovy
pipeline {

agent 
{
    label 'master'
}

    
    environment {
            SNP_IMG_TAG = "SNAP_1.0.${BUILD_NUMBER}"
                }
    

    
stages {

    stage('checkout SCM') {
        steps {
            script {
                checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: 'origin/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'ketanchhatbar', url: 'https://github.com/acc-trainings/customer-service']]]
                println "Branch Name : "+" ${env.BRANCH_NAME}"    
            }
        }
    }
    stage ('Build JAR')
        {
            steps {
            echo "Checkout completed. Starting the build"
            withMaven(maven: 'maven-latest') {
                sh 'mvn clean install package'
                //stash name:"jar", includes:"target/customer-service-*.jar"
            }
        }
        }

    stage('Login Into Quay') {
    steps {
        script {
        withCredentials([usernamePassword(credentialsId: 'QuayMonitor' , passwordVariable:'password', usernameVariable:'username')]) {
            sh '''
            docker --version
            docker login quay.io -u ${username} -p ${password}
            '''
    }
    }
    }   
    }    

    stage('Build Image with Quay') {
    steps {
        script {
        withCredentials([usernamePassword(credentialsId: 'QuayMonitor' , passwordVariable:'password', usernameVariable:'username')]) {
            sh '''
             docker info
             docker build . -t quay.io/necloudnativetraining/customer-service:${SNP_IMG_TAG}
            '''
    }
    }
    }   
    }    
    
    stage('Push Image to Quay') {
    steps {
        script {
        withCredentials([usernamePassword(credentialsId: 'QuayMonitor' , passwordVariable:'password', usernameVariable:'username')]) {
            sh '''
             docker login quay.io -u ${username} -p ${password}
             docker push quay.io/necloudnativetraining/customer-service:${SNP_IMG_TAG}
            '''
    }
    }
    }   
    }    
  
}
}
