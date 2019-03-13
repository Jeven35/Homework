package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.ResourceDao;
import com.jeven.mycourses.domain.Resource;
import com.jeven.mycourses.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeven on 2019/3/12.
 */
@Service
public class ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    public Resource getResourceByUrl(String url){
        Resource resource = resourceDao.findById(url).orElse(null);
        System.out.println(resource);
        return resource;
    }

    public List<Role> getRoles(int id){
        return resourceDao.getRoles(id);
    }


}
