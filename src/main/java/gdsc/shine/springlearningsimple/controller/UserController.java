package gdsc.shine.springlearningsimple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/users")
    public String pringHello(@RequestParam String name) {
        return "안녕하세요! " + name;
    }
}
