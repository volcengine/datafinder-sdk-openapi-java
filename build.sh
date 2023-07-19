#!/bin/sh
set -x
./mvnw -DskipTests clean package -U assembly:single

rm -rf release/*
zip -j release/sdk-openapi-java.zip target/sdk-openapi-java-*.jar README.md