package gdsc.shine.springlearningsimple.mvc.mapping;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.mvc.domain.User;

@RestController
@RequestMapping("/http-method")
public class HttpMethodController {

	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody User user) {
		Long id = 1L;
		return ResponseEntity.created(URI.create("/users/" + id)).build();
	}

	public ResponseEntity<List<User>> showUser() {
		List<User> users = Arrays.asList(
			new User("이름", "email"),
			new User("이름", "email")
		);
		return ResponseEntity.ok().body(users);
	}
}
