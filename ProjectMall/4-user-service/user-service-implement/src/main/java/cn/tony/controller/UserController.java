package cn.tony.controller;

import cn.tony.pojo.User;
import cn.tony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RefreshScope
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value(value="${wode.maya:}")
    private String name;

    //    登录
    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.queryUserByName(user.getUsername());
    }

//    注册
    @GetMapping("/register")
    public void register(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletResponse response) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreated(new Date());
        user.setState(0);
        userService.userRegister(user);
        response.sendRedirect("/mylogin.html");

    }

    @GetMapping("/find")
    public User query(String username){
        User user = new User();
        user.setUsername(name);
        User queryUserByName = userService.queryUserByName(name);
        //String s = JSONObject.toJSONString(queryUserByName);
        return queryUserByName;
    }




}
