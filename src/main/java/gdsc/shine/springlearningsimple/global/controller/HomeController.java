package gdsc.shine.springlearningsimple.global.controller;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private final ApplicationContext applicationContext;

	public HomeController(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@GetMapping("/hello")
	public String greetingWorld() {
		return "Hello World !!!";
	}

	@GetMapping("/beans")
	public String showAllBeans() {
		String[] beans = applicationContext.getBeanDefinitionNames();
		StringBuilder stringBuilder = new StringBuilder("<pre>");
		Arrays.stream(beans)
			.forEach(bean -> stringBuilder.append(bean).append("\n\n"));
		stringBuilder.append("</pre>");
		return stringBuilder.toString();
	}

}
