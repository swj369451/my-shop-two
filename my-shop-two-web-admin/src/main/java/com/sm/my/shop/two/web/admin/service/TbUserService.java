package com.sm.my.shop.two.web.admin.service;

import com.sm.my.shop.two.commons.persistence.BaseService;
import com.sm.my.shop.two.domain.TbUser;

public interface TbUserService extends BaseService<TbUser> {
    /**
     * 根据邮箱与密码查询用户信息
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);
}
