package gdsc.shine.springlearningsimple.mvc.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.mvc.domain.User;

@RestController
@RequestMapping("/uri-pattern")
public class UriPatternController {

	@GetMapping("/users/{id}")
	public ResponseEntity<User> pathVariable(@PathVariable Long id) {
		User user = new User(id, "이름", "email");
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/patterns/*")
	public ResponseEntity<String> pattern() {
		return ResponseEntity.ok().body("pattern");
	}

	public ResponseEntity<String> patternStars() {
		return ResponseEntity.ok().body("pattern-multi");
	}
}
