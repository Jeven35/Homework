package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.RoleDao;
import com.jeven.mycourses.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeven on 2019/3/12.
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Role> getRolesOfUser(String email)
    {
        return roleDao.findRolesOfUser(email);
    }

    public List<Role> getRolesOfResource(int id)
    {
        return roleDao.findRolesOfResource(id);
    }

}
