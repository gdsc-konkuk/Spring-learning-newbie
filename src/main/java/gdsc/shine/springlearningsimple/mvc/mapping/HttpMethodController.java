package gdsc.shine.springlearningsimple.mvc.mapping;

import gdsc.shine.springlearningsimple.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/http-method")
public class HttpMethodController {

    @PostMapping("users")
    public ResponseEntity createUser(@RequestBody User user) {
        long id = 1L;
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> showUser() {
        List<User> users = Arrays.asList(
                new User("이름1", "email1"),
                new User("이름2", "email2")
        );
        return ResponseEntity.ok().body(users);
    }
}
