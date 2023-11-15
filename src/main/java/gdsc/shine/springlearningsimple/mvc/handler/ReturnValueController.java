package gdsc.shine.springlearningsimple.mvc.handler;

import gdsc.shine.springlearningsimple.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/return-value")
public class ReturnValueController {

    /**
     * The return value is converted through
     * HttpMessageConverter implementations
     * and written to the response.
     * See @ResponseBody.
     */
    @GetMapping("message")
    @ResponseBody
    public String string() {
        return "message";
    }

    @GetMapping("users")
    @ResponseBody
    public User responseBodyForUser() {
        return new User("name", "email");
    }

    /**
     * The return value that specifies the full response
     * (including HTTP headers and body) is to be converted
     * through HttpMessageConverter implementations
     * and written to the response.
     * See ResponseEntity.
     */
    @GetMapping("users/{id}")
    public ResponseEntity<User> responseEntity(@PathVariable Long id) {
        return ResponseEntity.ok(new User("name", "email"));
    }

    @GetMapping("members")
    public ResponseEntity<Void> responseEntityFor400() {
        return ResponseEntity.badRequest().build();
    }

    /**
     * A view name to be resolved with ViewResolver implementations
     * and used together with the implicit model — determined
     * through command objects and @ModelAttribute methods.
     * The handler method can also programmatically enrich
     * the model by declaring a Model argument
     * (see Explicit Registrations).
     */
    @GetMapping("thymeleaf")
    public String thymeleaf() {
        return "sample";
    }
}
