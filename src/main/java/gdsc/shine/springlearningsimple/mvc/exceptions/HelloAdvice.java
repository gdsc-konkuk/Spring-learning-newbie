package gdsc.shine.springlearningsimple.mvc.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HelloAdvice {
	public ResponseEntity<String> handle() {
		return ResponseEntity.badRequest().body("HelloException");
	}
}
