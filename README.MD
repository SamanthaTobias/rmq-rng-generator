# RabbitMQ Random Number Generator

This is a Spring Boot application that generates random numbers and sends them to a RabbitMQ message broker. The application provides a REST API to start, stop, and set the period of the random number generator.

## Installation

To build and run the application, you will need to have Maven and Docker installed on your system.

1. Clone the repository to your local machine.
2. In the terminal, navigate to the root directory of the project.
3. Run the build-and-run.sh script to build the Maven project, build the Docker image, and run the Docker container.

`./build-and-run.sh`


The script will build the project, create a Docker image, and start a Docker container running the application on port 9050.

## Usage

To use the application, you can send HTTP requests to the REST API provided by the `NumberController` to `http://`.

### Start generating random numbers

To start generating random numbers, send an HTTP GET request to `/api/start`.

`http://localhost:9050/api/start`


The application will start generating random numbers and sending them to the RabbitMQ message broker.

### Stop generating random numbers

To stop generating random numbers, send an HTTP GET request to `/api/end`.

`http://localhost:9050/api/end`


The application will stop generating random numbers.

### Set the period for generating random numbers

To set the period for generating random numbers, send an HTTP GET request to `/api/setPeriod/{seconds}`, where `{seconds}` is the number of seconds between each generated number.

`http://localhost:9050/api/setPeriod/10`


The application will set the period for generating random numbers to 10 seconds.
