package io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.controller;

import io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NumberController {

	@Autowired
	private NumberService numberService;

	@GetMapping("/start")
	public String start() {
		numberService.startGenerating();
		return "Random number generation started";
	}

	@GetMapping("/end")
	public String end() {
		numberService.stopGenerating();
		return "Random number generation stopped";
	}

	@GetMapping("/setPeriod/{seconds}")
	public String setPeriod(@PathVariable int seconds) {
		numberService.setPeriod(seconds);
		return "Period for random number generation set to " + seconds + " seconds";
	}

}
