package gdsc.shine.springlearningsimple.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.domain.user.dao.HelloDao;

@RestController
public class UserController {

	private final HelloDao helloDao;

	public UserController(HelloDao helloDao) {
		this.helloDao = helloDao;
	}

	@GetMapping("/users")
	public String sayHello(@RequestParam String name) {
		return "안녕하세요 ! " + name;
	}

}
