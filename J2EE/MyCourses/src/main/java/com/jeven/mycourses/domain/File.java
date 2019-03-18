package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by jeven on 2019/3/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "files")
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    private int fid;

    private int cid;

    // 1表示课件，2表示作业，3表示成绩
    private int type;

    //本来名字
    private String fileName;

    //重新定义的名字
    private String reName;



}
