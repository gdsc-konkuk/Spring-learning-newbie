package gdsc.shine.springlearningsimple.controller;

import gdsc.shine.springlearningsimple.repository.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Users {

    private final HelloDao helloDao;

    @Autowired
    public Users(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @GetMapping("/users")
    public String sayHello(@RequestParam("name") String username) {
        this.helloDao.insertUserByName(username);

        int accessCount = this.helloDao.countByUserName(username);
        if (accessCount == 1) {
            return String.format("안녕하세요! %s", username);
        } else {
            return String.format("%s님 안녕하세요, %d번째 방문이시군요!", username, accessCount);
        }
    }
}
