#!/bin/bash

# Stop and remove any existing container
docker stop rabbitmq-random-number-generator >/dev/null 2>&1 && docker rm rabbitmq-random-number-generator >/dev/null 2>&1

# Build the Maven project
mvn clean package

# Build the Docker image
docker build -t rabbitmq-random-number-generator .

# Check if network exists, create it if it doesn't
if ! docker network inspect rabbitmq-network >/dev/null 2>&1; then
    docker network create rabbitmq-network
fi

# Run the Docker container
docker run --rm --name rabbitmq-random-number-generator --network rabbitmq-network -p 9050:9050 rabbitmq-random-number-generator
