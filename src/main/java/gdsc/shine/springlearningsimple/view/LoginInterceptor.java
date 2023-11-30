package gdsc.shine.springlearningsimple.view;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    //Filter -> DispatcherServlet -> "Interceptor" -> Handler
    //공통적인 인증 처리
    //PreHandle이기 때문에 Handler 실행 전에 예외 발생 가능

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");

        if (accessToken == null) {
            throw new AuthorizationException();
        }

        return super.preHandle(request, response, handler);
    }

}
