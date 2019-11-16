package cn.tony.controller;



import cn.tony.pojo.user;
import cn.tony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RefreshScope//热部署
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public user queryById(@PathVariable("id") Long id){

        System.out.println("输出："+id);
        return userService.queryById(id);
    }

    @GetMapping("ps")
    public void makeEncodePassWord(){
        String str ="admin";
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        CharSequence sequence = "admin".subSequence(0, 5);
        String s = encode.encode(sequence);
        System.out.println("密码"+s);
    }
}
