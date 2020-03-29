package com.sm.my.shop.two.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.my.shop.two.commons.persistence.BaseEntity;
import com.sm.my.shop.two.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * 用户表
 */
@Data
public class TbUser extends BaseEntity{
    @Length(min = 6,max = 20,message = "用户名必须介于6到20位直接")
    private String username;

    @JsonIgnore
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE,message = "手机格式不正确")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL,message = "邮箱格式不正确")
    private String email;
}
