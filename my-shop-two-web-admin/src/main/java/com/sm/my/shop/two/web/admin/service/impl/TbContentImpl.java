package com.sm.my.shop.two.web.admin.service.impl;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.dto.PageInfo;
import com.sm.my.shop.two.domain.TbContent;
import com.sm.my.shop.two.web.admin.dao.TbContentDao;
import com.sm.my.shop.two.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        //        通过验证
        BaseResult baseResult = check(tbContent);
        if (baseResult.getStatus() == BaseResult.FAIL_STATUS) {
            return baseResult;
        }

        if (tbContent.getId() == null) {
//            新增
            tbContent.setUpdated(new Date());
            tbContent.setCreated(new Date());
            tbContentDao.insert(tbContent);
            baseResult.setMessage("新增成功");
        } else {
//            更新
            tbContentDao.update(tbContent);
            baseResult.setMessage("保存成功");
        }

        return baseResult;
    }

    @Override
    public void delete(Long contentId) {
        tbContentDao.delete(contentId);
    }

    @Override
    public TbContent findById(Long contentId) {
        return tbContentDao.findById(contentId);
    }


    @Override
    public List<TbContent> pageByLimit(Map<String, Object> parameter) {
        return tbContentDao.pageByLimit(parameter);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {
        PageInfo<TbContent> baseEntityPageInfo = new PageInfo<>();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("start",start);
        parameter.put("length",length);
        parameter.put("tbContent",tbContent);

        Integer count = count(tbContent);
        baseEntityPageInfo.setDraw(draw);
        baseEntityPageInfo.setRecordsFiltered(count);
        baseEntityPageInfo.setRecordsTotal(count);
        baseEntityPageInfo.setData(tbContentDao.pageByLimit(parameter));
        return baseEntityPageInfo;
    }

    @Override
    public Integer count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }

    public BaseResult check(TbContent tbContent) {
        BaseResult baseResult = BaseResult.success();
        if (tbContent.getCategoryId() == null) {
            baseResult = BaseResult.fail("所属分类不能为空");
        }
        return baseResult;
    }
}
