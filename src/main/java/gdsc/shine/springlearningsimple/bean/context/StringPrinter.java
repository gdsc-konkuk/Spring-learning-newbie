package gdsc.shine.springlearningsimple.bean.context;

import org.springframework.stereotype.Component;

@Component
public class StringPrinter implements Printer {

    //StringBuilder랑 같은 역할
    //멀티쓰레드 환경에서 사용
    //그 외 환경에서는 StringBuilder 사용
    private StringBuffer buffer = new StringBuffer();

    @Override
    public void print(String message) {
        this.buffer.append(message);
    }

    public String toString() {
        return this.buffer.toString();
    }
}
