package cn.tony.pojo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;


public class AuthUserImpl implements AuthUser {

    private Long id;
    private String username;
    private String password;
    private Long userId;
    private String phone;
    private Date created;
    private Integer state;
    private List<RoleModel> roles;


    //    将权限存入SpringSecurity
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    //账户是否禁用
    @Override
    public boolean isAccountNonLocked() {
        if (state==0){
            return true;//0表示开启
        }else
            return false;
    }

    //是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    //    是否开启
    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthUserImpl{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", state=" + state +
                ", roles=" + roles +
                '}';
    }
}
