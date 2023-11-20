## note

### Don't use interceptors for security checks

Interceptors are not ideally suited as a security layer due to the potential for a mismatch with annotated controller
path matching, which can also match trailing slashes and path extensions transparently, along with other path matching
options.

Generally, we recommend using Spring Security which includes a dedicated MvcRequestMatcher to align with Spring MVC path
matching and also has a security firewall that blocks many unwanted characters in URL paths.

### Design interceptors

- Spring 영역 안에서 동작 (`Spring Context`, `Bean` 접근 가능)
- 시점
    - `preHandler` : Controller 전
    - `postHandler` : Controller 후, View Rendering 전
    - `afterCompletion` : View Rendering 후

### Design ArgumentResolvers

- Controller의 argument를 resolve
- 기본 제공 : `RequestBody`, `ModelAttribute` etc...
- implements `HandlerMethodArgumentResolver`
    - `supportsParameter` : parameter 처리 가능 여부 확인
    - `resolveArgument` : parameter를 기반으로 argument 생성
        - `ModelAndViewContainer` : 모델과 뷰 정보
        - `NativeWebRequest` : Web Request 정보
        - `WebDataBinderFactory`
