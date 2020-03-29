package com.sm.my.shop.two.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;
    private String phone;
    private String email;
}
