package gdsc.shine.springlearningsimple.bean.scan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name="lineDao")
    public LineDao LineDao() {
        return new LineDao();
    }

    @Bean(name="lineService")
    public LineService LineService() {
        return new LineService();
    }
}
