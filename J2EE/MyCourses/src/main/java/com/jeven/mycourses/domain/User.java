package com.jeven.mycourses.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by jeven on 2019/3/1.
 */
@Data
@Entity(name = "users")
@ToString
public class User {
    @Id
    private String email;
    private String password;
}
