package gdsc.shine.springlearningsimple.mvc.mapping;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param-header")
public class ParamHeaderController {

    @GetMapping(value = "/message",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> message() {
        return ResponseEntity.ok().body("message");
    }

    @GetMapping(value = "/message",produces = MediaType.APPLICATION_JSON_VALUE, params = "name=hello")
    public ResponseEntity<String> messageForParam(@RequestParam("name") String name) {
        return ResponseEntity.ok().body(name);
    }

    @GetMapping(value = "/message",produces = MediaType.APPLICATION_JSON_VALUE, headers = "HEADER=hi")
    public ResponseEntity<String> messageForHeader(@RequestHeader("HEADER") String header) {
        return ResponseEntity.ok().body(header);
    }
}
