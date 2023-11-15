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

    @GetMapping("users/{id}")
    public ResponseEntity<User> pathVariable(@PathVariable Long id) {
        User user = new User(id, "이름", "email");
        return ResponseEntity.ok().body(user);
    }

    /**
     * When multiple patterns match a URL,
     * the best match must be selected.
     * This is done with one of the following depending on
     * whether use of parsed PathPattern is enabled for use or not:
     * > PathPattern.SPECIFICITY_COMPARATOR
     */
    @GetMapping("patterns/?")
    public ResponseEntity<String> pattern() {
        return ResponseEntity.ok().body("pattern");
    }

    /**
     * The default mapping pattern (/**) is excluded
     * from scoring and always sorted last.
     * Also, prefix patterns (such as /public/**) are
     * considered less specific than other pattern
     * that do not have double wildcards.
     */
    @GetMapping("patterns/**")
    public ResponseEntity<String> patternStars() {
        return ResponseEntity.ok().body("pattern-multi");
    }
}
