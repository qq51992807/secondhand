package com.gk.secondhand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer id;//id
    private String userName;//用户名
    private Long phone;//账号
    private String password;//密码
    private String userRole;//角色

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
        this.userName = userName;
    }


    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    @Override
    public String toString() {
        return "Admin [id=" + id + ", userName=" + userName + ", phone=" + phone + ", password=" + password
                + ", userRole=" + userRole + "]";
    }


}
