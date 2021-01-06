# customer-service
this repo will be used to connect with mongodb

Followling commands can be used to deploy the code to openshift

oc new-app fabric8/s2i-java:latest-java11~https://github.com/acc-trainings/customer-service.git --name=customer-service
oc set env dc/customer-service spring.profiles.active=local
