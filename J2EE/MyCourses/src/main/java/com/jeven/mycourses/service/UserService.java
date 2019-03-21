package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.UserDao;
import com.jeven.mycourses.domain.User;
import com.jeven.mycourses.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jeven on 2019/3/12.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    public User saveUser(User user){
        return userDao.save(user);
    }


    public User findUserByEmail(String email){
        return userDao.getOne(email);
    }


    public void deleteUser(String email){
        User user = findUserByEmail(email);
        userDao.delete(user);
        return;
    }



    /**
     * 之后是security的方法
     * @return
     */

    @Transactional
    public List<User> getAllUser()
    {
        return userDao.findAll();
    }

    @Transactional
    public List<User> getByEmail(String email)
    {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("查找用户：" + s);
        User user = getByEmail(s).get(0);
        if(user == null)
        {
            throw new UsernameNotFoundException("没有该用户");
        }

        //查到User后将其封装为UserDetails的实现类的实例供程序调用
        //用该User和它对应的Role实体们构造UserDetails的实现类
        return new UserDetailsImpl(user, roleService.getRolesOfUser(user.getEmail()));
    }
}
