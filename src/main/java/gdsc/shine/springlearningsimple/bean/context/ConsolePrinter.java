package gdsc.shine.springlearningsimple.bean.context;

import org.springframework.stereotype.Component;

@Component
public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
