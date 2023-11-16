package gdsc.shine.springlearningsimple.mvc.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.mvc.domain.User;

@RestController
@RequestMapping("/uri-pattern")
public class UriPatternController {

	public ResponseEntity<User> pathVariable(Long id) {
		User user = new User(id, "이름", "email");
		return ResponseEntity.ok().body(user);
	}

	public ResponseEntity<String> pattern() {
		return ResponseEntity.ok().body("pattern");
	}

	public ResponseEntity<String> patternStars() {
		return ResponseEntity.ok().body("pattern-multi");
	}
}
