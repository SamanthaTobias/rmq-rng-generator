package io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.controller;

import io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Autowired
	private NumberService numberService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("currentState", numberService.getState());
		model.addAttribute("numbers", numberService.getNumbers());
		return "index";
	}

}
