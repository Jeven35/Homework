package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.Resource;
import com.jeven.mycourses.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by jeven on 2019/3/12.
 */
public interface ResourceDao extends JpaRepository<Resource, String> , JpaSpecificationExecutor<Resource> {
    @Query(value = "select r.* from role r, role_resource rr where rr.resid = ?1 and rr.rid = r.id", nativeQuery = true)
    List<Role> getRoles(int id);

}
