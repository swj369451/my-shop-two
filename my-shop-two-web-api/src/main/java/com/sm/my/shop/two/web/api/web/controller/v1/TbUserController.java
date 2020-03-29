package com.sm.my.shop.two.web.api.web.controller.v1;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.web.api.service.TbUserService;
import com.sm.my.shop.two.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);
        if(user==null){
            return BaseResult.fail("账号或密码为空");
        }else {
            TbUserDTO tbUserDTO=new TbUserDTO();
            BeanUtils.copyProperties(user,tbUserDTO);
            return BaseResult.success("登录成功",tbUserDTO);
        }
    }
}
