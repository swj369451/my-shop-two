package com.sm.my.shop.two.web.api.service.impl;

import com.sm.my.shop.two.domain.TbContent;
import com.sm.my.shop.two.domain.TbContentCategory;
import com.sm.my.shop.two.web.api.dao.TbContentDao;
import com.sm.my.shop.two.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> findByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);
        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.findByCategoryId(tbContent);
    }

    @Override
    public TbContent findById(Long id) {
        return null;
    }
}
