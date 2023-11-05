package gdsc.shine.springlearningsimple.controllers;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BeanController {
    private final AnnotationConfigApplicationContext ac;

    public BeanController(AnnotationConfigApplicationContext ac) {
        this.ac = ac;
    }

    @GetMapping("/beans")
    public List<String> getBeans() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        List<String> userBeans = new ArrayList<>();
        for(String beanDefinitionName : beanDefinitionNames) {
             BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
             if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                 userBeans.add(beanDefinitionName);
             }
        }
        return userBeans;
    }
}
