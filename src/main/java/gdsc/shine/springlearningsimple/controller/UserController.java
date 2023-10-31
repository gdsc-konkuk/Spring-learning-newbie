package gdsc.shine.springlearningsimple.controller;

import gdsc.shine.springlearningsimple.DAO.HelloDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private HelloDao helloDao;

    public UserController(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @GetMapping("/users")
    public String printHello(@RequestParam String name) {
        helloDao.insertUserByName(name);

        int visitedCnt =  helloDao.countByUserName(name);

        if(visitedCnt == 1) {
            return "안녕하세요! " + name;
        } else {
            return name + "님 안녕하세요, " + visitedCnt + "번째 방문이시군요!";
        }

    }
}
