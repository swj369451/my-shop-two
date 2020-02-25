package com.sm.my.shop.two.web.admin.dao;

import com.sm.my.shop.two.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {

    /**
     * 查询全部
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 插入
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 删除
     * @param userId
     */
    void delete(Long userId);

    /**
     * 根据id查询
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
     * 根据email查询用户
     * @param email
     * @return
     */
    TbUser findByEmail(String email);


    /**
     *根据id数组批量删除
     * @param userIds
     */
    void deleteMultiByIds(String[] userIds);

    /**
     * 分页查询
     * @param parameter
     * @return
     */
    List<TbUser> pageByLimit(Map<String,Object> parameter);

    /**
     * 总记录数
     * @return
     */
    Integer count(TbUser tbUser);
}
