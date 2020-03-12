package com.sm.my.shop.two.web.admin.service;

import com.sm.my.shop.two.commons.persistence.BaseService;
import com.sm.my.shop.two.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService extends BaseService<TbContentCategory> {

    /**
     * 根据父类节点Id查询所有子节点
     * @param parentId
     * @return
     */
    List<TbContentCategory> selectByParentId(Long parentId);
    
}
