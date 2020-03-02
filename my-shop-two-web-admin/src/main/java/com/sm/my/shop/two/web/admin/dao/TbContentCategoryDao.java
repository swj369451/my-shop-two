package com.sm.my.shop.two.web.admin.dao;

import com.sm.my.shop.two.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {

    /**
     * 查询全部分类
     * @return
     */
    List<TbContentCategory> selectAll();

    /**
     * 根据父类节点Id查询所有子节点
     * @param parentId
     * @return
     */
    List<TbContentCategory> selectByParentId(Long parentId);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    TbContentCategory findById(Long id);
}