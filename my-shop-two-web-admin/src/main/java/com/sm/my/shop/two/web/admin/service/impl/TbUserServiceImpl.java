package com.sm.my.shop.two.web.admin.service.impl;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.dto.PageInfo;
import com.sm.my.shop.two.commons.utils.RegexpUtils;
import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.web.admin.dao.TbUserDao;
import com.sm.my.shop.two.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
//        通过验证
        BaseResult baseResult = checkUser(tbUser);
        if (baseResult.getStatus() == BaseResult.FAIL_STATUS) {
            return baseResult;
        }

        if (tbUser.getId() == null) {
//            新增
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setUpdated(new Date());
            tbUser.setCreated(new Date());
            tbUserDao.insert(tbUser);
            baseResult.setMessage("新增成功");
        } else {
//            更新
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setUpdated(new Date());
            tbUserDao.updateUser(tbUser);
            baseResult.setMessage("保存成功");
        }

        return baseResult;
    }

    @Override
    public void delete(Long userId) {
        tbUserDao.delete(userId);
    }

    @Override
    public TbUser findById(Long userId) {
        return tbUserDao.findById(userId);
    }

    @Override
    public void updateUser(TbUser tbUser) {
        tbUserDao.updateUser(tbUser);
    }


    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.findByEmail(email);
        if (tbUser != null && tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return tbUser;
        }
        return null;
    }


    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMultiByIds(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length, int draw,TbUser tbUser) {
        PageInfo<TbUser> baseEntityPageInfo = new PageInfo<>();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("start",start);
        parameter.put("length",length);
        parameter.put("tbUser",tbUser);

        Integer count = count(tbUser);
        baseEntityPageInfo.setDraw(draw);
        baseEntityPageInfo.setRecordsFiltered(count);
        baseEntityPageInfo.setRecordsTotal(count);
        baseEntityPageInfo.setData(tbUserDao.pageByLimit(parameter));
        return baseEntityPageInfo;
    }

    @Override
    public Integer count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

    public BaseResult checkUser(TbUser tbUser) {
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
