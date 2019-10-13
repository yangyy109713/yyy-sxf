package com.yyy.rutu.sxfy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(indexName = "sxfy", type = "operation")
public class FUser {
    @Id
    private Integer id;

    private String userName;

    private String password;

    private String realName;

    private String email;

    //解决报错： Failed to convert from type [java.lang.String] to type [java.util.Date] for value '2019-09-29
    //如果已在application.properties中指定日期格式（spring.mvc.date-format=yyyy-MM-dd），则不需要下面的注解
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private Integer deptId;

    private FDept dept;

    private Integer gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public FDept getDept() {
        return dept;
    }

    public void setDept(FDept dept) {
        this.dept = dept;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "FUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", deptId=" + deptId +
                '}';
    }
}