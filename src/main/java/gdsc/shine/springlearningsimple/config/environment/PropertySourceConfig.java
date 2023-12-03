package gdsc.shine.springlearningsimple.config.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

// Java-based Configuration을 하기 위한 클래스로 지정하기
// application.properties 파일을 활용하기 위한 설정 추가하기
@Configuration
@PropertySource("classpath:/application.properties")
public class PropertySourceConfig {
    private final Environment env;

    @Autowired
    public PropertySourceConfig(Environment env) {
        this.env = env;
    }

    // application.properties의 security.jwt.token.secret-key 값을 활용하여 JwtTokenKeyProvider를 빈으로 등록하기
    @Bean
    public JwtTokenKeyProvider jwtTokenKeyProvider() {
        String secretKey = this.env.getProperty(("security.jwt.token.secret-key"));
        return new JwtTokenKeyProvider(secretKey);
    }
}
