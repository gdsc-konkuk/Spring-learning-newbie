package gdsc.shine.springlearningsimple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Users {
    @GetMapping("/users")
    public String sayHello(@RequestParam("name") String username){
        return String.format("안녕하세요! %s", username);
    }
}
