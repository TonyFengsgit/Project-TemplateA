package cn.tony.pojo;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "user01")
@Data
public class user implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String addr;
    private String username;
    private String password;
    private Long age;
    private String sex;
    private Long  accountId;//mybatis 自带按照驼峰进行拆分，所以表一定是 带下划线"_"

}
