package cn.tony.pojo;

import java.io.Serializable;

public class Token implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private UserInfo userInfo;
    private Integer expireTime;

    public Integer getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}