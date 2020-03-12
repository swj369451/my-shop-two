package com.sm.my.shop.two.commons.persistence;

import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> {

    /**
     * 插入
     * @param entity
     */
    void insert(T entity);

    /**
     * 更新
     * @param entity
     */
    void update(T entity);


    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     *根据id数组批量删除
     * @param ids
     */
    void deleteMultiByIds(String[] ids);

    /**
     * 查询全部
     * @return
     */
    List<T> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     * @param parameter
     * @return
     */
    List<T> pageByLimit(Map<String,Object> parameter);

    /**
     * 总记录数
     * @return
     */
    Integer count(T entity);
}
