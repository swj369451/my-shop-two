package com.sm.my.shop.two.web.admin.dao;

import com.sm.my.shop.two.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {

    /**
     * 查询全部
     * @return
     */
    List<TbContent> selectAll();

    /**
     * 插入
     * @param tbContent
     */
    void insert(TbContent tbContent);

    /**
     * 删除
     * @param contentId
     */
    void delete(Long contentId);

    /**
     * 根据id查询
     * @param contentId
     * @return
     */
    TbContent findById(Long contentId);

    /**
     * 更新用户
     * @param tbContent
     */
    void update(TbContent tbContent);


    /**
     *根据id数组批量删除
     * @param contentIds
     */
    void deleteMultiByIds(String[] contentIds);

    /**
     * 分页查询
     * @param parameter
     * @return
     */
    List<TbContent> pageByLimit(Map<String,Object> parameter);

    /**
     * 总记录数
     * @return
     */
    Integer count(TbContent tbContent);
}
