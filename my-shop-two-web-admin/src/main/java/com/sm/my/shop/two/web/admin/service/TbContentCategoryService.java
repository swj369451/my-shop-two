package com.sm.my.shop.two.web.admin.service;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {

    /**
     * 查询所有的内容分类
     * @return
     */
    List<TbContentCategory> findAll();

    /**
     * 根据父类节点Id查询所有子节点
     * @param parentId
     * @return
     */
    List<TbContentCategory> selectByParentId(Long parentId);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    TbContentCategory findById(Long id);

    /**
     * 保存或更新数据
     * @param tbContentCategory
     * @return
     */
    BaseResult save(TbContentCategory tbContentCategory);
}
