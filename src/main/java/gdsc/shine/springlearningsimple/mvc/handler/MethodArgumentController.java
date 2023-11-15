package gdsc.shine.springlearningsimple.mvc.handler;

import gdsc.shine.springlearningsimple.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/method-argument")
public class MethodArgumentController {

    /**
     * If a method argument is not matched to any of the earlier values
     * in this table and it is a simple type
     * (as determined by BeanUtils#isSimpleProperty),
     * it is resolved as a @RequestParam.
     * Otherwise, it is resolved as a @ModelAttribute.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> requestParam(
            @RequestParam(value = "name", defaultValue = "guest") String userName) {
        List<User> users = Arrays.asList(
                new User(userName, "email"),
                new User(userName, "email")
        );
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users/body")
    public ResponseEntity requestBody(@RequestBody User user) {
        User newUser = new User(1L, user.getName(), user.getEmail());
        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
    }

}
