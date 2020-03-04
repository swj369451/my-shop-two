package com.sm.my.shop.two.web.admin.service.impl;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.domain.TbContentCategory;
import com.sm.my.shop.two.web.admin.dao.TbContentCategoryDao;
import com.sm.my.shop.two.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> findAll() {
        List<TbContentCategory> sourceContentCategories = tbContentCategoryDao.selectAll();
        List<TbContentCategory> targetContentCategories = new ArrayList<>();
        sortList(sourceContentCategories,targetContentCategories,0L);
        return targetContentCategories;
    }

    @Override
    public List<TbContentCategory> selectByParentId(Long parentId) {
        return tbContentCategoryDao.selectByParentId(parentId);
    }

    @Override
    public TbContentCategory findById(Long id) {
        return tbContentCategoryDao.findById(id);
    }

    public void sortList(List<TbContentCategory> sourceList,
                                            List<TbContentCategory> targetList,
                                            Long id) {
        for (TbContentCategory tbContentCategory : sourceList) {
            if(tbContentCategory.getParentId().equals(id)){
                targetList.add(tbContentCategory);

//                判断是否有子节点
                if(tbContentCategory.getIsParent()){
                    for(TbContentCategory contentCategory : sourceList){
//                        寻找子节点
                        if(contentCategory.getId().equals(tbContentCategory.getId())){
                            sortList(sourceList,targetList,contentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public BaseResult save(TbContentCategory tbContentCategory) {
//        通过验证
        BaseResult baseResult = BaseResult.success();
        if (tbContentCategory.getId() == null) {
//            新增
            tbContentCategory.setUpdated(new Date());
            tbContentCategory.setCreated(new Date());
            tbContentCategoryDao.insert(tbContentCategory);
            baseResult.setMessage("新增成功");
        } else {
//            更新
            tbContentCategory.setUpdated(new Date());
            tbContentCategoryDao.updateUser(tbContentCategory);
            baseResult.setMessage("保存成功");
        }

        return baseResult;
    }
}
