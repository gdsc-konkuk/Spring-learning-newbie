package gdsc.shine.springlearningsimple.mvc.mapping;

import gdsc.shine.springlearningsimple.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uri-pattern")
public class UriPatternController {

    // 숫자가 path variable로 왔을 때.
    @GetMapping(path = "/users/{id:[1-9]*}")
    public ResponseEntity<User> pathVariable(@PathVariable Long id) {
        User user = new User(id, "이름", "email");
        return ResponseEntity.ok().body(user);
    }

    // 테스트 코드 상에서는 한 글자의 영문 단어에 대해서 처리하는 것으로 보임.
    @GetMapping(path = "/patterns/{pattern:[a-z]}")
    public ResponseEntity<String> pattern() {
        return ResponseEntity.ok().body("pattern");
    }

    // 나머지 모든거 처리
    @GetMapping(path = "/patterns/**")
    public ResponseEntity<String> patternStars() {
        return ResponseEntity.ok().body("pattern-multi");
    }
}
