package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by jeven on 2019/3/12.
 */
@Repository
@Table(name = "role")
public interface RoleDao extends JpaRepository<Role, Integer>,
        JpaSpecificationExecutor<Role> {
    //自定义sql语句并且开启本地sql
    //根据用户名查找该用户所有权限
    @Query(value = "select r.* from role r, user_role ur where ur.email = ?1 and ur.rid = r.id", nativeQuery = true)
    List<Role> findRolesOfUser(String email);

    //根据resource的主键查找resource允许的所有权限
    @Query(value = "select r.* from role r, resource_role rr where rr.res_id = ?1 and rr.rid = r.id", nativeQuery = true)
    List<Role> findRolesOfResource(long resourceId);

}
