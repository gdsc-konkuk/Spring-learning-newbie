package gdsc.shine.springlearningsimple.controllers;

import gdsc.shine.springlearningsimple.dao.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final HelloDao helloDao;
    @Autowired
    public UserController(HelloDao helloDao) {
        this.helloDao = helloDao;
    }
    @GetMapping("/users")
    public String getUser(@RequestParam("name") String name){
        int prevVisitCount;

        try {
            prevVisitCount= helloDao.countByUserName(name).orElse(0);
            helloDao.insertUserName(name);
            if(prevVisitCount == 0) {
                return "안녕하세요!" + name;
            } else {
                return name + "님 안녕하세요, " + (prevVisitCount + 1) + "번째 방문이시군요.";
            }
        } catch (Exception exception) {
            return "서버 에러";
        }
    }

    @PostMapping("/users")
    public void joinUser(@RequestParam("name") String name) {
        this.helloDao.insertUserName(name);
    }
    @GetMapping("/users/cnt")
    public int cntUser(@RequestParam("name") String name) {
        return this.helloDao.countByUserName(name).orElse(0);
    }
}
