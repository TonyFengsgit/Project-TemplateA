package cn.tony.mapper;

import cn.tony.pojo.User;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    User queryByName(String name);
}
