package com.sm.my.shop.two.web.api.dao;

import com.sm.my.shop.two.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao {
    TbUser login(TbUser tbUser);
}
