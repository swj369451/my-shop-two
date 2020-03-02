package com.sm.my.shop.two.web.admin.web.controller;

import com.sm.my.shop.two.domain.TbContentCategory;
import com.sm.my.shop.two.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @ModelAttribute
    public TbContentCategory getTbContent(Long contentId){
        if(contentId == null){
            return new TbContentCategory();
        }else {
            return tbContentCategoryService.findById(contentId);
        }

    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", tbContentCategoryService.findAll());
        return "content_category_list";
    }

    /**
     * 查询类的树结构
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "treeDate", method = RequestMethod.POST)
    public List<TbContentCategory> treeDate(Long id) {
        if (id == null) {
            id = 0L;
        }

        return tbContentCategoryService.selectByParentId(id);
    }

    /**
     * 跳转到分类表单页面
     * @return
     */
    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String contentForm(){
        return "content_category_form";
    }



}
