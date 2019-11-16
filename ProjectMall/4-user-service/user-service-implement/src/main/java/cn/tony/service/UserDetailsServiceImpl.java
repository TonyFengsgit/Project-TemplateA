package cn.tony.service;

import cn.tony.pojo.AuthUserImpl;
import cn.tony.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUserByName(username);
        System.out.println("第一次："+user);
        AuthUserImpl authUser = new AuthUserImpl();
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        authUser.setId(user.getId());
        authUser.setState(user.getState());
        authUser.setUserId(user.getUserId());
        authUser.setPhone(user.getPhone());
        System.out.println("第二次："+authUser.toString());
        return authUser;
    }
}
