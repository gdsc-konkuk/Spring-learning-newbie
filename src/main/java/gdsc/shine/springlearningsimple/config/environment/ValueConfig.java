package gdsc.shine.springlearningsimple.config.environment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기!
// TODO: application.properties 파일을 활용하기 위한 설정 추가하기!
// TODO: gdsc.shine.springlearningsimple.config.environment 내에 있는 스프링 빈을 스캔하기!
@Configuration
@ComponentScan(basePackages = {"gdsc.shine.springlearningsimple.config.environment"})
@PropertySource("classpath:application.properties")
public class ValueConfig {


}
