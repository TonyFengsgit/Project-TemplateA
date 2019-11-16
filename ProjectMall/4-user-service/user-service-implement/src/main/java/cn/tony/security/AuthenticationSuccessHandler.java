package cn.tony.security;


import cn.tony.pojo.AuthUserImpl;
import cn.tony.pojo.Token;
import cn.tony.pojo.UserInfo;
import cn.tony.service.BuildTokenService;
import cn.tony.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 登录成功处理类
 *
 * @author Exrickx
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private BuildTokenService buildTokenService;

    @Autowired
    private UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");
        RequestCache cache = new HttpSessionRequestCache();//一定不能在configure(HttpSecurity http)中将session禁用，重定向靠session
        SavedRequest savedRequest = cache.getRequest(request, response);

        AuthUserImpl userDetails =  (AuthUserImpl) auth.getPrincipal();//获取用户信息从security
        System.out.println("第三次 : "+userDetails.toString());
        String tokenKey = buildTokenService.BuildTokenKey(userDetails.getUsername(), userDetails.getPassword());
        System.out.println("创建key  : "+tokenKey);

        //保存用户登录信息
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        String agent = request.getHeader("User-Agent");

        log.info("ip地址"+ip);
        log.info("URL路径"+url);
        log.info("客户端"+agent);

        UserInfo info = new UserInfo();
        info.setIp(ip);
        info.setUrl(url);
        info.setUserAgent(agent);
        info.setUserId(userDetails.getUserId());
        info.setUsername(userDetails.getUsername());
        info.setCreated(new Date());
        System.out.println("第四次："+info);
        userService.addUserinfo(info);


        Token token = new Token();
        token.setUserInfo(info);
        boolean flag = buildTokenService.set(tokenKey,token , 400L);
        if (flag){
            System.out.println("redis key  创建成功");
            UUID uuid = UUID.randomUUID();
            System.out.println("uuid  ： "+uuid);
            String value = buildTokenService.get(tokenKey).toString();
            System.out.println("从Redis中取出值  "+value);
        }
        response.addHeader("token",tokenKey);
        response.sendRedirect("/index.html");
    }

}
