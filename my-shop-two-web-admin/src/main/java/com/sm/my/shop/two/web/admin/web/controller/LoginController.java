package com.sm.my.shop.two.web.admin.web.controller;

import com.sm.my.shop.two.commons.constant.ControllerConstant;
import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.web.admin.service.TbUserService;
import com.sm.my.shop.two.web.admin.web.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转到登录页
     *
     * @return
     */
    @RequestMapping(value = {"", "/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request,
                        HttpServletResponse response) {
        String userInfo = CookieUtils.getCookieValue(request, ControllerConstant.SESSION_USER);
        if(userInfo != null){
            String[] userInfoArray = userInfo.split(":");
            String email= userInfoArray[0];
            String password= userInfoArray[1];
            request.setAttribute("email",email);
            request.setAttribute("password",password);
            request.setAttribute("isRemember",true);
        }
        return "login";
    }

    /**
     * 登录用户
     * @param email
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true) String password,
                        Model model,
                        String isRemember,
                        HttpSession session) {

        TbUser tbUser = tbUserService.login(email, password);

        if(tbUser == null){
//            登录失败
            model.addAttribute("message","账号或密码错误");
            return "login";
        }
        else{
//            登录成功
            session.setAttribute(ControllerConstant.SESSION_USER, tbUser);
            return "redirect:/main";

        }
    }

    /**
     * 注销用户
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session,
                         HttpServletRequest request,
                         HttpServletResponse response) {
//        使session无效
        session.invalidate();
        return "redirect:/login";
    }
}
