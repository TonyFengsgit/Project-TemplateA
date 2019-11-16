package cn.tony.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @program: AccessInterceptor
 * @description: TODO
 * @author: Chonzi.xiao
 * @create: 2019-07-17 10:18
 */


public class AccessInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessInterceptor.class);

    /*用拦截器进行拦截方法，获得token*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String token = request.getHeader("token");
            System.out.println("token is : " + token);
            if (token.isEmpty() || token == null) {
                response.reset();
                response.setContentType("application/json;charset=UTF-8");
                response.addHeader("token", "不存在token");
                return false;
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            // 从方法处理器中获取出要调用的方法
//            Method method = handlerMethod.getMethod();
//            // 获取出方法上的Access注解
//            Access access = method.getAnnotation(Access.class);
//            if(access != null && access.ignoreToken()){
//                return true;
//            }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}
