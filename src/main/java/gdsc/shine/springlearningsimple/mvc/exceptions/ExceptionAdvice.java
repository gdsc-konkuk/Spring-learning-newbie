package gdsc.shine.springlearningsimple.mvc.exceptions;

import gdsc.shine.springlearningsimple.mvc.exceptions.exception.HiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * On startup, RequestMappingHandlerMapping and ExceptionHandlerExceptionResolver
 * detect controller advice beans and apply them at runtime.
 * Global @ExceptionHandler methods, from an @ControllerAdvice,
 * are applied after local ones, from the @Controller.
 */
@RestControllerAdvice(assignableTypes = {ExceptionsController.class})
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handle(HiException ex) {
        return ResponseEntity.badRequest().body("HiException");
    }
}
