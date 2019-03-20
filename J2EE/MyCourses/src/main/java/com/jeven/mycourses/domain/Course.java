package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by jeven on 2019/3/15.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "courses")
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private int id;

    @Column(name = "cname")
    private String courseName;

    @Column(name = "uid")
    private String tEmail;

    @Column(name = "des")
    private String courseDes;

    private int number;

    private int chosen;

    @Column(name = "classtime")
    private String classTime;

    private Date start;

    private Date end;

    private int state;

    private String teacherName;
}
