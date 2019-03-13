package com.jeven.mycourses.domain;

import com.jeven.mycourses.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jeven on 2019/3/12.
 * 实现spring security的部分接口
 */
public class UserDetailsImpl implements UserDetails {

    private String email;
    private String password;

    //包含着用户对应的所有Role，在使用时调用者给对象注入roles
    private List<Role> roles;

    @Autowired
    private RoleService roleService;

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    //无参构造
    public UserDetailsImpl() {
    }

    //用User构造
    public UserDetailsImpl(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    //用User和List<Role>构造
    public UserDetailsImpl(User user, List<Role> roles) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = roles;
    }

    public List<Role> getRoles()
    {
        return roles;
    }


    @Override
    //返回用户所有角色的封装，一个Role对应一个GrantedAuthority
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
