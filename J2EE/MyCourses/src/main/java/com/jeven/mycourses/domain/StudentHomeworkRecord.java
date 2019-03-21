package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by jeven on 2019/3/21.
 * 学生的作业提交记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student_homework")
@ToString
public class StudentHomeworkRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    // 老师布置的作业名(加后缀)
    private String homeworkName;

    // 课程ID
    private int courseID;

    // 作业提交状态(默认为"未提交"）
    private String state;

    //作业提交完整名(包括后缀)
    private String fullName;

    // 记录作业截止日期
    private Date ddl;

}
