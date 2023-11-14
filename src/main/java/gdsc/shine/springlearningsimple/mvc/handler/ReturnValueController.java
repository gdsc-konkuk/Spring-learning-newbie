package gdsc.shine.springlearningsimple.mvc.handler;

import gdsc.shine.springlearningsimple.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/return-value")
public class ReturnValueController {

    @GetMapping("/message")
    public ResponseEntity string() {
        return ResponseEntity.ok().body("message");

    }

    @GetMapping("/users")
    public ResponseEntity responseBodyForUser() {
        User user = new User("name", "email");
        return ResponseEntity.ok().body(user);

    }
    @GetMapping("/users/{id}")
    public ResponseEntity responseEntity(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new User("name", "email"));
    }

    @GetMapping("/members")
    public ResponseEntity responseEntityFor400() {
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/thymeleaf")
    public ResponseEntity thymeleaf() {
        return ResponseEntity.ok().body("Hello");
    }
}
