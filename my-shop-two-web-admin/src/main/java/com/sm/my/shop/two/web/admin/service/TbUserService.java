package com.sm.my.shop.two.web.admin.service;

import com.sm.my.shop.two.commons.dto.PageInfo;
import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.commons.dto.BaseResult;

import java.util.List;

public interface TbUserService {
    List<TbUser> selectAll();

    BaseResult save(TbUser tbUser);

    void deleteUser(Long userId);

    TbUser findById(Long userId);

    void updateUser(TbUser tbUser);

    List<TbUser> selectByUserName(String userName);

    TbUser login(String email,String password);

    List<TbUser> search(TbUser tbUser);

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
    PageInfo<TbUser> page(String start, String length,int draw);

    /**
     * 查询总记录数
     * @return
     */
    Integer count();
}
