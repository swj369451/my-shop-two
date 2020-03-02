package com.sm.my.shop.two.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.my.shop.two.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * 用户表
 */
@Data
public class TbUser extends BaseEntity{
//    todo 1.无法使用validate
//    @Length(min = 6,max = 20,message = "用户名必须介于6到20位直接")
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;


}
