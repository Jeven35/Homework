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
 * Created by jeven on 2019/3/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "role")
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role_name;
}
