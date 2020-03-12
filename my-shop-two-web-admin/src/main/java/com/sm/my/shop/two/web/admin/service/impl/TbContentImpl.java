package com.sm.my.shop.two.web.admin.service.impl;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.persistence.BaseServiceImpl;
import com.sm.my.shop.two.domain.TbContent;
import com.sm.my.shop.two.web.admin.dao.TbContentDao;
import com.sm.my.shop.two.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TbContentImpl extends BaseServiceImpl<TbContent,TbContentDao> implements TbContentService {
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
            dao.insert(tbContent);
            baseResult.setMessage("新增成功");
        } else {
//            更新
            dao.update(tbContent);
            baseResult.setMessage("保存成功");
        }

        return baseResult;
    }


    public BaseResult check(TbContent tbContent) {
        BaseResult baseResult = BaseResult.success();
        if (tbContent.getTbContentCategory().getId() == null) {
            baseResult = BaseResult.fail("所属分类不能为空");
        }
        return baseResult;
    }
}
