package com.sm.my.shop.two.web.admin.service.impl;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.persistence.BaseServiceImpl;
import com.sm.my.shop.two.commons.validator.BeanValidator;
import com.sm.my.shop.two.domain.TbContentCategory;
import com.sm.my.shop.two.web.admin.dao.TbContentCategoryDao;
import com.sm.my.shop.two.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl extends BaseServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService {


    @Override
    public List<TbContentCategory> selectAll() {
        List<TbContentCategory> sourceContentCategories = dao.selectAll();
        List<TbContentCategory> targetContentCategories = new ArrayList<>();
        sortList(sourceContentCategories, targetContentCategories, 0L);
        return targetContentCategories;
    }

    @Override
    public List<TbContentCategory> selectByParentId(Long parentId) {
        return dao.selectByParentId(parentId);
    }


    @Override
    public Integer count(TbContentCategory entity) {
        return null;
    }

    public void sortList(List<TbContentCategory> sourceList,
                         List<TbContentCategory> targetList,
                         Long id) {
        for (TbContentCategory tbContentCategory : sourceList) {
            if (tbContentCategory.getParent().getId().equals(id)) {
                targetList.add(tbContentCategory);

//                判断是否有子节点
                if (tbContentCategory.getIsParent()) {
                    for (TbContentCategory contentCategory : sourceList) {
//                        寻找子节点
                        if (contentCategory.getId().equals(tbContentCategory.getId())) {
                            sortList(sourceList, targetList, contentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public BaseResult save(TbContentCategory tbContentCategory) {
        String validator = BeanValidator.validator(tbContentCategory);
//        1.验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }

//        2.通过验证
//        初始化实体类
        tbContentCategory.setStatus(1);
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setIsParent(false);

//        3.判断是否有选择父类
        TbContentCategory parent = tbContentCategory.getParent();
        if(parent==null || parent.getId()==null){
//            没有选择父类，则为根目录
            parent.setId(0L);
            tbContentCategory.setIsParent(true);
        }

//        4.判断是新增还是更新
        if (tbContentCategory.getId() == null) {
//            新增
//            修改父节点为isParent
            TbContentCategory currentCategoryParent = findById(parent.getId());
            if(currentCategoryParent!=null){
                currentCategoryParent.setIsParent(true);
                update(currentCategoryParent);
            }
            tbContentCategory.setCreated(new Date());
            dao.insert(tbContentCategory);
        } else {
//            更新
            dao.update(tbContentCategory);
        }

        return BaseResult.success("保存成功");
    }
}
