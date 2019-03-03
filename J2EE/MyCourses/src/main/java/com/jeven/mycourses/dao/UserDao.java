package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by jeven on 2019/3/3.
 */
@Repository
@Table(name = "users")
public interface UserDao extends JpaRepository<User,String> {
}
