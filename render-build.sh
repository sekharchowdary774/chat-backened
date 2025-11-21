#!/bin/bash

# install java 17
export JAVA_VERSION=17
curl -fsSL https://deb.nodesource.com/setup_18.x | bash -
apt-get update
apt-get install -y openjdk-17-jdk

# verify java
java -version

# give mvnw permission
chmod +x mvnw

# build project
./mvnw clean package -DskipTests