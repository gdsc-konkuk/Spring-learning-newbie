package gdsc.shine.springlearningsimple.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import gdsc.shine.springlearningsimple.view.LoginInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	/**
	 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-view-controller
	 *
	 * "/" 요청 시 hello.html 페이지 응답하기
	 *
	 * `ParameterizableViewController`를 정의하는 단축 방법
	 * 호출될 때 즉시 특정 뷰로 포워딩
	 * 뷰가 응답을 생성하기 전에 실행할 Java 컨트롤러 로직이 없는 정적인 경우에 사용 가능
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("hello");
	}

	/**
	 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-interceptors
	 *
	 * "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
	 *
	 * Java configuration에서 들어오는 요청에 적용할 인터셉터를 등록 가능
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
