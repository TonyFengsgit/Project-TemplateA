package cn.tony.service;



import cn.tony.mapper.UserMapper;
import cn.tony.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public user queryById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
