package gdsc.shine.springlearningsimple.mvc.handler;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.mvc.domain.User;

@RestController
@RequestMapping("/method-argument")
public class MethodArgumentController {

	@GetMapping("/users")
	public ResponseEntity<List<User>> requestParam(String name) {
		List<User> users = Arrays.asList(
			new User(name, "rhrjsgh97@gmail.com"),
			new User(name, "rhrjsgh97@naver.com")
		);
		return ResponseEntity.ok().body(users);
	}

	@PostMapping("/users/body")
	public ResponseEntity requestBody(@RequestBody User user) {
		User newUser = new User(1L, user.getName(), user.getEmail());
		return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
	}

}
