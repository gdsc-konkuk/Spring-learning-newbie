package gdsc.shine.springlearningsimple.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeansController {

    private final ApplicationContext ac;

    @GetMapping("/beans")
    public String printBeans(){
        String[] beanNames = ac.getBeanDefinitionNames();
        StringBuilder sb = new StringBuilder();
        for (String beanName : beanNames) {
            sb.append("bean : " + beanName);
            sb.append("\n");
        }
        return sb.toString();
    }
}
