# 학습 정리
# 1. ViewController and ViewResolver
***
## ViewController
- view와 매핑되는 컨트롤러
- 특정 url과 매핑되는 view 이름 반환
## ViewResolver
- View Controller에 의해 반환된 이름을 View Resolver에서 실제 View 객체로 매핑

# 2. HandlerInterceptor and Filter
***
## Interceptor
- DispatcherServlet 실행 후 동작
- Spring Context에 접근 용이
```java
public interface HandlerInterceptor {
    // 컨트롤러 동작 전에 실행
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        return true;
    }
    
    // 컨트롤러 동작 이후 실행
    // DispatcherServlet의 View 처리 전에 동작
    default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
    }

    // DispatcherServlet의 View 처리 이후 동작
    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
    }

}

```
## Filter
- Java Servlet에서 제공
- DispatcherServlet 처리 전, 후에 동작
- Spring Context에 접근 어려움
- FilterChain으로 연쇄 동작 가능
```java
public interface Filter {
    // 웹 컨테이너 생성 시 실행
    default void init(FilterConfig filterConfig) throws ServletException {
    }
    
    // Request, Response가 Filter를 거칠 때 실행
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException;

    // Filter 소멸 시 실행
    default void destroy() {
    }
}
```

# 3. ArgumentResolver
***
- Controller로 들어온 parameter를 가공, 수정하는 객체
- @RequestBody, @PathVariable 등을 통해 값을 받아올 떄 HandlerMethodArgumentResolver 사용

```java
public interface HandlerMethodArgumentResolver {
    // 해당 메소드를 Override하여 파라미터 타입 검사
    // true일 경우 다음 로직 진행
	boolean supportsParameter(MethodParameter parameter);

    // 해당 메소드를 Override하여 데이터 가공, 수정
	@Nullable
	Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception;

}
```