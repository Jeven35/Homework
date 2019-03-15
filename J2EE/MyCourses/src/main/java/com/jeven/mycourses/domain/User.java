package com.jeven.mycourses.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by jeven on 2019/3/1.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@ToString
public class User {
    @Id
    private String email;
    private String password;
    private String name;
    private String tel;
    private String sex;
    private int status = 0;
    private String validateCode;
    private Date registerTime;


    @Transient
    public Date getLastActivateTime() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(registerTime);
        cl.add(Calendar.DATE , 2);

        return cl.getTime();
    }
}
