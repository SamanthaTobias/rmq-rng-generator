package io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class NumberServiceTest {

	@Autowired
	private NumberService numberService;

	@MockBean
	private RabbitTemplate rabbitTemplate;

	@MockBean
	private AmqpAdmin amqpAdmin;

	@BeforeEach
	public void setUp() {
		reset(rabbitTemplate, amqpAdmin);
	}

	@Test
	public void testStartGenerating() {
		numberService.startGenerating();
		verify(rabbitTemplate, timeout(6000).atLeast(1)).convertAndSend(anyString(), anyString(), anyInt());
		numberService.stopGenerating();
	}

	@Test
	public void testStopGenerating() {
		numberService.startGenerating();
		numberService.stopGenerating();
		verify(rabbitTemplate, timeout(12000).times(1)).convertAndSend(anyString(), anyString(), anyInt());
	}

	@Test
	public void testSetPeriod() {
		numberService.setPeriod(2);
		numberService.startGenerating();
		verify(rabbitTemplate, timeout(3000).atLeast(2)).convertAndSend(anyString(), anyString(), anyInt());
		numberService.stopGenerating();
	}

}
