package cn.tony.config;

import cn.tony.interceptor.AccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    //让拦截器生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //将拦截器加入配置，使生效
        registry.addInterceptor(new AccessInterceptor())
                .addPathPatterns("/**")     //对所有的路径进行拦截
              //  .excludePathPatterns("/regist/**")  //排除某些路径
                ;
    }

    //springbootweb依赖的跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}
