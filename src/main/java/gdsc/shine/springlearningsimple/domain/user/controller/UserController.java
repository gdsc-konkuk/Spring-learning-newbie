package gdsc.shine.springlearningsimple.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.domain.user.application.UserService;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String sayHello(@RequestParam String name) {
		userService.insertUserByName(name);
		int visitedCount = userService.countByUserName(name);
		if (visitedCount == 1) {
			return "안녕하세요 ! " + name;
		}
		return name + "님 안녕하세요, " + visitedCount + "번째 방문이시군요 ! 허허허";
	}

}
