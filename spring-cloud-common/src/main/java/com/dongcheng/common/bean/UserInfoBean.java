package com.dongcheng.common.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {

    private Long id;

    private String account;

    private Integer status;

    private String password;

    public UserInfoBean(Long id, String account, Integer status, String password) {
        this.id = id;
        this.account = account;
        this.status = status;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

