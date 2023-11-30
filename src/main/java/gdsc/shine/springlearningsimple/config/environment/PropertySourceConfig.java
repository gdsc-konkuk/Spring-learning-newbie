package gdsc.shine.springlearningsimple.config.environment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
// TODO: application.properties 파일을 활용하기 위한 설정 추가하기
@Configuration
@PropertySource("classpath:application.properties")
public class PropertySourceConfig {

    private final Environment env;
    private final ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
    private final ConfigurableEnvironment env2 = ctx.getEnvironment();
    private final MutablePropertySources propertySources = env2.getPropertySources();

    public PropertySourceConfig(Environment env) throws IOException {
        this.env = env;
    }
    
    // TODO: application.properties의 security.jwt.token.secret-key 값을 활용하여 JwtTokenKeyProvider를 빈으로 등록하기
    @Bean
    public JwtTokenKeyProvider jwtTokenKeyProvider() {
        return new JwtTokenKeyProvider(env.getProperty("security.jwt.token.secret-key"));
    }

    @Bean
    public JwtTokenKeyProvider2 jwtTokenKeyProvider2() {
        try {
            propertySources.addLast(new ResourcePropertySource("classpath:application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new JwtTokenKeyProvider2(env2.getProperty("security.jwt.token.secret-key"));
    }
}
