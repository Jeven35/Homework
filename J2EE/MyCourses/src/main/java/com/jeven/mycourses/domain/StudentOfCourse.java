package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by jeven on 2019/3/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "studentofcourses")
@ToString
public class StudentOfCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private int courseID;

    @Column(name = "state")
    // 退课记录,0表示没退课，1表示退课
    private int State;
}
