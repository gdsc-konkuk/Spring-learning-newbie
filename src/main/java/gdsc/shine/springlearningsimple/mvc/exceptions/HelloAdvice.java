package gdsc.shine.springlearningsimple.mvc.exceptions;

import org.springframework.http.ResponseEntity;

public class HelloAdvice {
	public ResponseEntity<String> handle() {
		return ResponseEntity.badRequest().body("HelloException");
	}
}
