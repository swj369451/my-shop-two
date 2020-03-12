package com.sm.my.shop.two.web.admin.service.impl;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.persistence.BaseServiceImpl;
import com.sm.my.shop.two.commons.utils.RegexpUtils;
import com.sm.my.shop.two.commons.validator.BeanValidator;
import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.web.admin.dao.TbUserDao;
import com.sm.my.shop.two.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class TbUserServiceImpl extends BaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
//        验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }

//        通过验证
        BaseResult baseResult = BaseResult.success();
        if (tbUser.getId() == null) {
//            新增
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setUpdated(new Date());
            tbUser.setCreated(new Date());
            dao.insert(tbUser);
            baseResult.setMessage("新增成功");
        } else {
//            更新
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setUpdated(new Date());
            dao.update(tbUser);
            baseResult.setMessage("保存成功");
        }

        return baseResult;
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.findByEmail(email);
        if (tbUser != null && tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return tbUser;
        }
        return null;
    }

    public BaseResult checkTbContent(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();
        if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("用户不能为空");
        } else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("密码不能为空");
        } else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机不能为空");
        } else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机格式错误");
        } else if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空");
        } else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱格式错误");
        }
        return baseResult;
    }
}
