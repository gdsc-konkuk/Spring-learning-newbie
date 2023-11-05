package gdsc.shine.springlearningsimple.controller;


import gdsc.shine.springlearningsimple.dao.UserDAO;
import gdsc.shine.springlearningsimple.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDAO userDAO;

    @GetMapping("/users")
    public ResponseEntity<String> findUserByName(@RequestParam String name) {
        log.info("UserController.findUserByName");

        userDAO.insertUserByName(name);

        int visitCount = userDAO.countByUserName(name);

        if (visitCount == 1)
            return ResponseEntity.ok("안녕하세요! " + name);

        return ResponseEntity.ok(name + "님 안녕하세요, " + visitCount + "번째 방문이시군요!");
    }
}
