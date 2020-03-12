package com.sm.my.shop.two.commons.persistence;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.dto.PageInfo;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    /**
     * 查询所有数据
     * @return
     */
    List<T> selectAll();


    /**
     * 保存或更新数据
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 更新用户
     * @param entity
     */
    void update(T entity);

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
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总记录数
     * @return
     */
    Integer count(T entity);
}
