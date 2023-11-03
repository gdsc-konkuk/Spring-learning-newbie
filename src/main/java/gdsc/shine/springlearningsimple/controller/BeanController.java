package gdsc.shine.springlearningsimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {

    private final ApplicationContext ac;

    @Autowired
    public BeanController(ApplicationContext ac) {
        this.ac = ac;
    }

    @GetMapping("/beans")
    public String[] getCustomList() {
        return this.ac.getBeanDefinitionNames();
    }
}
