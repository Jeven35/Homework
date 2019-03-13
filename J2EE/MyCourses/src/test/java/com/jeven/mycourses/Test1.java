package com.jeven.mycourses;

import com.jeven.mycourses.domain.User;
import com.jeven.mycourses.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jeven on 2019/3/13.
 */
public class Test1 extends TestFather {

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        User user = userService.findUserByEmail("123");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
        user.setPassword(encodedPassword);
        userService.saveUser(user);
    }

}
