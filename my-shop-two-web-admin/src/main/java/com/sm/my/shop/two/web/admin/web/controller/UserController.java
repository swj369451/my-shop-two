package com.sm.my.shop.two.web.admin.web.controller;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.dto.PageInfo;
import com.sm.my.shop.two.domain.TbUser;
import com.sm.my.shop.two.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * @ModelAttribute的特点
     * 1.在所有@RequestMapping注解方法调用前执行
     * 2.返回的对象自动放到model里面去,key可能是返回对象的首字母小写
     */
    @ModelAttribute
    public TbUser getTbUser(Long userId){
        if(userId == null){
            return new TbUser();
        }else {
            return tbUserService.findById(userId);
        }

    }

    /**
     * 用户列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
//        DataTables会自动请求数据,所以以下俩句代码就不需要了
//        List<TbUser> tbUsers = tbUserService.selectAll();
//        model.addAttribute("list",tbUsers);
        return "/userList";
    }

    /**
     * 跳转到用户表单页面
     * @return
     */
    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String userForm(){
        return "/user_form";
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult result = BaseResult.fail("删除用户失败");
        if(StringUtils.isNoneBlank(ids)){
            tbUserService.deleteMulti(ids.split(","));
            result=BaseResult.success("删除用户成功");
        }
        return result;
    }

    /**
     * 保存与新增用户
     * @param tbUser
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(TbUser tbUser,
                       Model model,
                       RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbUserService.save(tbUser);

        if(baseResult.getStatus() == BaseResult.SUCCESS_STATUS){
//            保存成功
            redirectAttributes.addFlashAttribute("result",baseResult);
            return "redirect:/user/list";
        }else{
//            保存失败
            model.addAttribute("result",baseResult);
            model.addAttribute("tbUser",tbUser);
            return "user_form";
        }
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser){
        Integer draw = Integer.valueOf(request.getParameter("draw"));
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));

        return tbUserService.page(start, length, draw,tbUser);
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(){
        return "user_detail";
    }
}

