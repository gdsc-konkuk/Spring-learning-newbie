package gdsc.shine.springlearningsimple.mvc.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/param-header")
public class ParamHeaderController {

    @GetMapping("/message")
    public ResponseEntity<String> message() {
        return ResponseEntity.ok().body("message");
    }

    @GetMapping("/messageForParam")
    public ResponseEntity<String> messageForParam(@RequestParam("name") String name) {
        return ResponseEntity.ok().body(name);
    }

    public ResponseEntity<String> messageForHeader() {
        return ResponseEntity.ok().body("hi");
    }
}
