#!/bin/bash

# Check if network exists, create it if it doesn't
if ! docker network inspect rabbitmq-network >/dev/null 2>&1; then
    docker network create rabbitmq-network
fi

docker run -d --name rabbitmq --network rabbitmq-network -p 5672:5672 -p 15672:15672 rabbitmq:3-management
