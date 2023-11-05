package gdsc.shine.springlearningsimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class BeanController {

    private final GenericApplicationContext ac;

    @Autowired
    public BeanController(GenericApplicationContext ac) {
        this.ac = ac;
    }

    @GetMapping("/beans")
    public String[] getCustomList() {
        return Arrays.stream(this.ac.getBeanDefinitionNames())
                .filter(beanName -> this.ac.getBeanDefinition(beanName).getRole() == BeanDefinition.ROLE_APPLICATION)
                .toArray(String[]::new);
    }
}
