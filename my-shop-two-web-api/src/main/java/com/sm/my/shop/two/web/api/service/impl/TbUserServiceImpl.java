package com.sm.my.shop.two.web.api.service.impl;

import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.web.api.dao.TbUserDao;
import com.sm.my.shop.two.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl implements TbUserService {
    
    @Autowired
    private TbUserDao tbUserDao;
    
    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);
        if(user!=null){
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if(password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
