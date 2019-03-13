package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by jeven on 2019/3/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "resource")
@ToString
public class Resource {
    private int id;

    @Id
    private String url;

    @Column(name = "res_name")
    private String resName;
}
