package com.sm.my.shop.two.web.admin.dao;

import com.sm.my.shop.two.commons.persistence.BaseDao;
import com.sm.my.shop.two.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 根据email查询用户
     * @param email
     * @return
     */
    TbUser findByEmail(String email);
}
