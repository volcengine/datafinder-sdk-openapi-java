#!/bin/sh

exec ./mvnw -DskipTests clean package -U assembly:single

rm -rf release/javasdk.zip
zip -j release/javasdk.zip target/javasdk-1.1.0.jar target/javasdk-1.1.0-jar-with-dependencies.jar README.md
