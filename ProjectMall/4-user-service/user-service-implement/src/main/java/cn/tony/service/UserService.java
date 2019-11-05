package cn.tony.service;


import cn.tony.mapper.UserMapper;
import cn.tony.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUsers(User user){

        User one = userMapper.selectOne(user);

        return one;
    }
}
