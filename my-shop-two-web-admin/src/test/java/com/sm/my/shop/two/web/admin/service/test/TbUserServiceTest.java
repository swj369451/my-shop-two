package com.sm.my.shop.two.web.admin.service.test;

import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        System.out.println();
    }

    @Test
    public void testInsertUser(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("小");
        tbUser.setEmail("222@qq.com");
        tbUser.setPhone("18955555555");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }


    @Test
    public void testFindUserById(){
        tbUserService.findById(33L);
        System.out.println();
    }

    @Test
    public void testUpdateUser(){
        TbUser tbUser = tbUserService.findById(33L);
        tbUser.setUsername("滴");
        tbUserService.update(tbUser);
    }

    @Test
    public void testMD5(){
        String md5DigestAsHex = DigestUtils.md5DigestAsHex("12345".getBytes());
        System.out.println(md5DigestAsHex);
    }
}
