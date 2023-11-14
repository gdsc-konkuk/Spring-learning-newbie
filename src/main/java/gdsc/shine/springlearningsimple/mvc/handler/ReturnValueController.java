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

//    public void responseBodyForUser() {
//        return new User("name", "email");
//    }
//
//    public void responseEntity(@PathVariable Long id) {
//        return ResponseEntity.ok(new User("name", "email"));
//    }
//
//    public void responseEntityFor400() {
//        return ResponseEntity.badRequest().build();
//    }
//
//    public void thymeleaf() {
//        return "sample";
//    }
}
