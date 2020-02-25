package com.sm.my.shop.two.web.admin.dao;

import com.sm.my.shop.two.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbUserDao {

    List<TbUser> selectAll();

    void insert(TbUser tbUser);

    void delete(Long userId);

    TbUser findById(Long userId);

    void updateUser(TbUser tbUser);

    List<TbUser> selectByUserName(String userName);

    TbUser findByEmail(String email);

    List<TbUser> search(TbUser tbUser);

    /**
     *根据id数组批量删除
     * @param userIds
     */
    void deleteMultiByIds(String[] userIds);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    List<TbUser> pageByLimit(int start,int length);

    /**
     * 总记录数
     * @return
     */
    Integer count();
}
