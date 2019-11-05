package cn.tony.controller;



import cn.tony.pojo.user;
import cn.tony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public user queryById(@PathVariable("id") Long id){

        System.out.println("输出："+id);
        return userService.queryById(id);
    }
}
