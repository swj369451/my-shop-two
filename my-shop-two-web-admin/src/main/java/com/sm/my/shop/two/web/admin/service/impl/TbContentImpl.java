package com.sm.my.shop.two.web.admin.service.impl;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.validator.BeanValidator;
import com.sm.my.shop.two.domain.TbContent;
import com.sm.my.shop.two.web.admin.abstracts.BaseServiceImpl;
import com.sm.my.shop.two.web.admin.dao.TbContentDao;
import com.sm.my.shop.two.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbContentImpl extends BaseServiceImpl<TbContent,TbContentDao> implements TbContentService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
//        验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }

        BaseResult baseResult = BaseResult.success();
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

}
