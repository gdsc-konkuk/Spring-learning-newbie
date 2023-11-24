package gdsc.shine.springlearningsimple.mvc.handler;

import gdsc.shine.springlearningsimple.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/method-argument")
public class MethodArgumentController {

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> requestParam(String name) {
//    public ResponseEntity<List<User>> requestParam(@RequestParam String name) {
    public ResponseEntity<List<User>> requestParam(@RequestParam("name") String name) {
        List<User> users = Arrays.asList(
                new User(name, "email"),
                new User(name, "email")
        );
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> requestParam(@ModelAttribute User user) {
        List<User> users = Arrays.asList(
                new User(user.getName(), "email"),
                new User(user.getName(), "email")
        );
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users/body")
    public ResponseEntity requestBody(@RequestBody User user) {
        User newUser = new User(1L, user.getName(), user.getEmail());
        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
    }

}
