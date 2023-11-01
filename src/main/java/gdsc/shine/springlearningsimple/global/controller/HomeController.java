package gdsc.shine.springlearningsimple.global.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private final ApplicationContext applicationContext;

	public HomeController(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@GetMapping("/home")
	public String greetingWorld() {
		return "Hello World !!!";
	}
}
