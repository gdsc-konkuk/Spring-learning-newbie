package gdsc.shine.springlearningsimple.config.environment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기!
// TODO: application.properties 파일을 활용하기 위한 설정 추가하기!
// TODO: gdsc.shine.springlearningsimple.config.environment 내에 있는 스프링 빈을 스캔하기!
@Configuration
@PropertySource("application.properties")
public class ValueConfig {

    private final Environment env;

    public ValueConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public JwtTokenExpireProvider jwtTokenExpireProvider() {
        return new JwtTokenExpireProvider(Long.parseLong(env.getProperty("security.jwt.token.expire-length")));
    }
}
