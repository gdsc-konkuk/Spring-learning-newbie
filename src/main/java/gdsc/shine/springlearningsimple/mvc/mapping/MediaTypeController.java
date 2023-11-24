package gdsc.shine.springlearningsimple.mvc.mapping;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.mvc.domain.User;

@RestController
@RequestMapping("/media-type")
public class MediaTypeController {

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createUser(@RequestBody User user) {
		long id = 1L;
		return ResponseEntity.created(URI.create("/users/" + id)).build();
	}

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> showUser() {
		List<User> users = Arrays.asList(
			new User("고건호", "rhrjsgh97@gmail.com"),
			new User("고뭉남", "rhrjsgh97@naver.com")
		);
		return ResponseEntity.ok().body(users);
	}

	@GetMapping(value = "/users", produces = MediaType.TEXT_HTML_VALUE)
	public String userPage() {
		return "user page";
	}
}
