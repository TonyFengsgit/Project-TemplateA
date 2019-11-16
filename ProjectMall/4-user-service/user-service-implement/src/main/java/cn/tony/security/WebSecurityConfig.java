package cn.tony.security;

import cn.tony.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("**/user/register/**")
                .antMatchers("/register.html");//忽略某些路径

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//CSRF protection is enabled by default in the Java configuration.即禁用 Spring Security 自带的跨域处理
                .authorizeRequests()//批准请求  ps:在这位置可以使用.antMatchers("/spitter/me").hasRole("SPITTER")对某个路径请求进行特殊处理
            //    .antMatchers("/user/register/**").permitAll()
                .anyRequest()//无特殊请求
                .authenticated().and().formLogin()//没有登录认证应用都要进行表单登录
                .loginPage("/mylogin.html")
                .loginProcessingUrl("/user/login")//指定登录路径
               // .successForwardUrl("/index.html")
                //.defaultSuccessUrl("/index.html")   // 访问指定页面，用户未登入，跳转至登入页面，如果登入成功，跳转至用户访问指定页面，用户访问登入页面，默认的跳转页面
                .permitAll()//.permitAll()方法允许请求没有任何的安全限制
                .successHandler(successHandler)//成功跳转处理器路径
                .and().cors()              //跨域处理使用cors跨域
               // .and().exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler())// PS:因为framework中已经对AccessDeniedException异常做了处理，此处不生效
                .and().sessionManagement()  // 定制我们自己的 session 策略
              //  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  //调整为让 Spring Security 不创建和不使用 session
              //  .and().addFilterBefore(new ImageValidateFilter(captchaProperties, redisTemplate), UsernamePasswordAuthenticationFilter.class)
               // .addFilter(new JWTAuthenticationFilter(authenticationManager,new DefaultAuthenticationEntryPoint(), redisTemplate, securityFactory))
        ;

    }

    //用于从数据库读取用户数据并且与输入的账户比较，是自动调用方法进行查询的
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

