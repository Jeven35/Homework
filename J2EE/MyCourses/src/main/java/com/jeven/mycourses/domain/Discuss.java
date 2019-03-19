package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by jeven on 2019/3/19.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "discuss")
@ToString
public class Discuss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cid;

    private String qName;

    private String email;

    private String uName;
}
