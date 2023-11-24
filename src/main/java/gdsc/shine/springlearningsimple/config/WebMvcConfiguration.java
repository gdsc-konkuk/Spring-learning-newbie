package gdsc.shine.springlearningsimple.config;

import gdsc.shine.springlearningsimple.view.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Map;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    /**
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-view-controller
     *
     * "/" 요청 시 hello.html 페이지 응답하기
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello");
    }

    /**
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-interceptors
     *
     * "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**");
    }

    /**
     * https://www.baeldung.com/spring-mvc-custom-data-binder#1-custom-argument-resolver
     *
     * AuthenticationPrincipalArgumentResolver 등록하기
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }
}
