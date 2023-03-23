package io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class RabbitMqRandomNumberGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqRandomNumberGeneratorApplication.class, args);
	}

}
