package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by jeven on 2019/3/21.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRoleDaoTest {
    @Autowired
    private UserRoleDao dao;

    @Test
    public void add() {
        UserRole userRole = new UserRole();
        userRole.setEmail("TT@163.COM");
        userRole.setRid(1);
        dao.save(userRole);
}
}