package gdsc.shine.springlearningsimple.config;

import gdsc.shine.springlearningsimple.view.AuthenticationPrincipalArgumentResolver;
import gdsc.shine.springlearningsimple.view.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    /**
     * "/" 요청 시 hello.html 페이지 응답하기
     * <p>
     * addViewControllers
     * You can use it in static cases when there is no Java controller logic to run before the view generates the response.
     * If an @RequestMapping method is mapped to a URL for any HTTP method then a view controller cannot be used to handle the same URL.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello");
    }

    /**
     * "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
     * <p>
     * You can register interceptors to apply to incoming requests.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**");
    }

    /**
     * https://www.baeldung.com/spring-mvc-custom-data-binder#1-custom-argument-resolver
     * <p>
     * AuthenticationPrincipalArgumentResolver 등록하기
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver());
    }
}
