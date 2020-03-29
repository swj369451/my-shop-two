package com.sm.my.shop.two.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.utils.EmailUtils;
import com.sm.my.shop.two.web.ui.api.ContentAPI;
import com.sm.my.shop.two.web.ui.api.UserAPI;
import com.sm.my.shop.two.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private EmailUtils emailUtils;

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("ppts", ContentAPI.ppt());
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {
        TbUser user = UserAPI.login(tbUser);
        if (!checkVerification(tbUser, request)) {
            //验证码错误
            model.addAttribute("baseResult", BaseResult.fail("验证码错误"));
            return "login";
        }
        if (user == null) {
            //登录失败
            model.addAttribute("baseResult", BaseResult.fail("账号或密码输入错误"));
            return "login";
        } else {
            //登录成功
            //发送邮件
            emailUtils.send("客户登录邮箱",
                    String.format("用户：%s 已登录myshop系统", user.getUsername()), "1964852743@qq.com");
            request.getSession().setAttribute("tbUser", user);
            return "redirect:index";
        }
    }

    /**
     * 检查验证码
     *
     * @param tbUser
     * @param request
     * @return
     */
    private boolean checkVerification(TbUser tbUser, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(code, tbUser.getVerification())) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        return index(model);
    }
}
