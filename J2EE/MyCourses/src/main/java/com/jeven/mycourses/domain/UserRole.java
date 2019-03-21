package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jeven on 2019/3/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_role")
@ToString
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String email;

    private int rid;
}
