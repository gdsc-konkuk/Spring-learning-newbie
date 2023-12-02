package gdsc.shine.springlearningsimple.config.javaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Java-based Configuration을 하기 위한 클래스로 지정하기
@Configuration
public class AuthenticationPrincipalConfig {

    // AuthService 빈을 등록하기
    @Bean
    public AuthService authService() {
        return new AuthService();
    }

    // AuthenticationPrincipalArgumentResolver를 빈 등록하고 authService에 대한 의존성을 주입하기
    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver(authService());
    }
}
