package gdsc.shine.springlearningsimple.mvc.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdsc.shine.springlearningsimple.mvc.exceptions.exception.CustomException;
import gdsc.shine.springlearningsimple.mvc.exceptions.exception.HelloException;

@RestController
@RequestMapping("/exceptions")
public class ExceptionsController {

	@GetMapping("/hello")
	public ResponseEntity exceptionHandler() {
		throw new CustomException();
	}

	@GetMapping("/hi")
	public ResponseEntity exceptionHandler2() {
		throw new HelloException();
	}

	public ResponseEntity<String> handle() {
		return ResponseEntity.badRequest().body("CustomException");
	}
}
