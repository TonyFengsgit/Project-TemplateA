package cn.tony.pojo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthUserImpl implements AuthUser {

    private Long id;
    private String username;
    private String password;
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
        if (state.equals(0)){
            return true;//0表示开启
        }else
            return false;
    }

    //是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }


    //    是否开启
    @Override
    public boolean isEnabled() {
        return true;
    }
}
