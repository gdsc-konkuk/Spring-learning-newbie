package gdsc.shine.springlearningsimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BeanController {
    private final ApplicationContext ac;

    public BeanController(ApplicationContext ac) {
        this.ac = ac;
    }

    @GetMapping("/beans")
    public List<String> getBeans() {
        return Arrays.asList(ac.getBeanDefinitionNames());
    }
}
