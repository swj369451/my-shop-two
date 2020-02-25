package com.sm.my.shop.two.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.my.shop.two.commons.persistence.BaseEntity;

public class TbUser extends BaseEntity{
    private String username;
    private String password;
    private String phone;
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
