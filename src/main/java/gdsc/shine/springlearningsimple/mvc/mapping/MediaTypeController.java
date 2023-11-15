package gdsc.shine.springlearningsimple.mvc.mapping;

import gdsc.shine.springlearningsimple.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@RestController
@RequestMapping("/media-type")
public class MediaTypeController {

    @PostMapping(value = "users", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        long id = 1L;
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @GetMapping(value = "users", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> showUser() {
        List<User> users = Arrays.asList(
                new User("이름1", "email1"),
                new User("이름2", "email2")
        );
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "users", produces = TEXT_HTML_VALUE)
    public String userPage() {
        return "user page";
    }
}
