package gdsc.shine.springlearningsimple.mvc.exceptions;

import gdsc.shine.springlearningsimple.mvc.exceptions.exception.HelloException;
import gdsc.shine.springlearningsimple.mvc.exceptions.exception.HiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exceptions")
public class ExceptionsController {

    @GetMapping("/hello")
    public ResponseEntity helloHandler() {
        throw new HelloException();
    }

    @GetMapping("/hi")
    public ResponseEntity hiHandler() {
        throw new HiException();
    }

    /**
     * We generally recommend that you be as specific
     * as possible in the argument signature,
     * reducing the potential for mismatches between
     * root and cause exception types.
     * <p>
     * <p>
     * In a multi-@ControllerAdvice arrangement,
     * we recommend declaring your primary root exception mappings
     * on a @ControllerAdvice prioritized with a corresponding order.
     * While a root exception match is preferred to a cause,
     * this is defined among the methods of a given
     * controller or @ControllerAdvice class.
     * This means a cause match
     * on a higher-priority @ControllerAdvice bean is preferred to any match
     * (for example, root)
     * on a lower-priority @ControllerAdvice bean.
     */
    @ExceptionHandler
    public ResponseEntity<String> handle(HelloException ex) {
        return ResponseEntity.badRequest().body("HelloException");
    }
}
