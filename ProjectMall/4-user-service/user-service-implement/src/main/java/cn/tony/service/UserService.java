package cn.tony.service;


import cn.tony.mapper.UserInfoMapper;
import cn.tony.mapper.UserMapper;
import cn.tony.pojo.User;
import cn.tony.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public UserInfoMapper userInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //查找用户
    public User queryUserByName(String name){
        User user = new User();
        user.setUsername(name);
        User one=userMapper.selectOne(user);
        return one;
    }

    //记录用户登录信息
    public void addUserinfo(UserInfo userInfo){
        int i = userInfoMapper.insert(userInfo);

    }


    //注册用户
    public void userRegister(User user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CharSequence ps=user.getPassword();//将String密码转化成charsequence
        String password = passwordEncoder.encode(ps);//进行编码
        user.setPassword(password);
        user.setUserId(System.currentTimeMillis());//
        int totals = userMapper.insert(user);

    }

}
