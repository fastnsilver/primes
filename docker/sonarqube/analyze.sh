#!/usr/bin/env bash

set -e

export SONAR_HOST=$(docker-machine ip $(docker-machine active))

cd ../..
mvn clean verify 
mvn sonar:sonar \
  -Dsonar.host.url=http://$SONAR_HOST:9000 \
  -Dsonar.jdbc.url=jdbc:postgresql://$SONAR_HOST/sonar