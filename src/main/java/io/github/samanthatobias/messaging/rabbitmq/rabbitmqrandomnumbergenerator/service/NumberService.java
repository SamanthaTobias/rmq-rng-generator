package io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class NumberService {

	private static final Logger log = LoggerFactory.getLogger(NumberService.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Value("${spring.rabbitmq.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.routingkey}")
	private String routingKey;

	@Value("${spring.rabbitmq.queue}")
	private String queue;

	@Value("${spring.rabbitmq.durable}")
	private boolean durable;

	private final List<Integer> numbers = new ArrayList<>();

	private ScheduledExecutorService executor;
	private int period = 5;

	@PostConstruct
	public void init() {
		DirectExchange directExchange = new DirectExchange(exchange, true, false);
		Queue queueObject = new Queue(queue, true);
		Binding binding = BindingBuilder.bind(queueObject).to(directExchange).with(routingKey);

		amqpAdmin.declareExchange(directExchange);
		amqpAdmin.declareQueue(queueObject);
		amqpAdmin.declareBinding(binding);
	}

	public void startGenerating() {
		if (executor != null) {
			executor.shutdown();
		}
		executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(() -> {
			int randomNumber = (int) (Math.random() * 100);
			numbers.add(randomNumber);
			log.info("Sending random number: {}", randomNumber);
			rabbitTemplate.convertAndSend(exchange, routingKey, randomNumber);
		}, 0, period, TimeUnit.SECONDS);
		log.info("Starting executor, period is {} seconds", period);
	}

	public void stopGenerating() {
		if (executor != null) {
			executor.shutdown();
			log.info("Shut down executor");
		}
	}

	public void setPeriod(int seconds) {
		if (seconds < 1) {
			throw new IllegalArgumentException("Seconds must be greater than 0, is " + seconds);
		}
		this.period = seconds;
		log.info("Set period to {} seconds", period);
	}

	public List<Integer> getNumbers() {
		return new ArrayList<>(numbers);
	}

	public String getState() {
		if (executor == null) {
			return "INACTIVE";
		} else {
			return "ACTIVE";
		}
	}

}
