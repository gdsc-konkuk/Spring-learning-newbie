package gdsc.shine.springlearningsimple.mvc.exceptions;

import gdsc.shine.springlearningsimple.mvc.exceptions.exception.CustomException;
import gdsc.shine.springlearningsimple.mvc.exceptions.exception.HelloException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ExceptionHandler({CustomException.class, HelloException.class})
    public ResponseEntity<String> handle(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getClass().getSimpleName());
    }
}
