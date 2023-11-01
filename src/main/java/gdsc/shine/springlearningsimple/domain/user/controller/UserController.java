package gdsc.shine.springlearningsimple.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.domain.user.repository.UserRepository;

@RestController
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public String sayHello(@RequestParam String name) {
		int visitedCount = userRepository.countByUserName(name);
		if (visitedCount == 0) {
			userRepository.insertUserByName(name);
			return "안녕하세요 ! " + name;
		}
		userRepository.insertUserByName(name);
		return name + "님 안녕하세요, " + visitedCount + "번째 방문이시군요 ! 허허허";
	}

}
