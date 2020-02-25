package com.sm.my.shop.two.web.admin.service;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.dto.PageInfo;
import com.sm.my.shop.two.domain.TbUser;

import java.util.List;

public interface TbUserService {
    /**
     * 查询所有数据
     * @return
     */
    List<TbUser> selectAll();


    /**
     * 保存或更新数据
     * @param tbUser
     * @return
     */
    BaseResult save(TbUser tbUser);

    /**
     * 根据id删除用户
     * @param userId
     */
    void delete(Long userId);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    TbUser findById(Long userId);

    /**
     * 更新用户
     * @param tbUser
     */
    void updateUser(TbUser tbUser);

    /**
     * 根据邮箱与密码查询用户信息
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页
     * @param start
     * @param length
     * @return
     */
    PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser);

    /**
     * 查询总记录数
     * @return
     */
    Integer count(TbUser tbUser);
}
