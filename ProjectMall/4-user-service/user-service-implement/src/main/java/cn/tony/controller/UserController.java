package cn.tony.controller;

import cn.tony.pojo.User;
import cn.tony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public User login(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userService.queryUsers(user);
    }
}
