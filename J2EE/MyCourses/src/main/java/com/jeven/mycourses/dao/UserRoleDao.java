package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.User;
import com.jeven.mycourses.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by jeven on 2019/3/21.
 */
@Repository
@Table(name = "user_role")
public interface UserRoleDao extends JpaRepository<UserRole,Integer> {
}
