package gdsc.shine.springlearningsimple.mvc.exceptions;

import gdsc.shine.springlearningsimple.mvc.exceptions.exception.HelloException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HelloAdvice {
    @ExceptionHandler(HelloException.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.badRequest().body("HelloException");
    }
}
