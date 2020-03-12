package com.sm.my.shop.two.web.admin.dao;

import com.sm.my.shop.two.commons.persistence.BaseDao;
import com.sm.my.shop.two.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao extends BaseDao<TbContentCategory> {
    /**
     * 根据父类节点Id查询所有子节点
     * @param parentId
     * @return
     */
    List<TbContentCategory> selectByParentId(Long parentId);
}