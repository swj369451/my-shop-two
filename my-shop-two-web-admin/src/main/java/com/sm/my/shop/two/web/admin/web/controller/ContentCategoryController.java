package com.sm.my.shop.two.web.admin.web.controller;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.domain.TbContentCategory;
import com.sm.my.shop.two.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @ModelAttribute
    public TbContentCategory getTbContent(Long contentId) {
        if (contentId == null) {
            return new TbContentCategory();
        } else {
            return tbContentCategoryService.findById(contentId);
        }

    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", tbContentCategoryService.selectAll());
        return "content_category_list";
    }

    /**
     * 查询类的树结构
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "treeDate", method = RequestMethod.POST)
    public List<TbContentCategory> treeDate(Long id) {
        if (id == null) {
            id = 0L;
        }
        List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectByParentId(id);
        return tbContentCategories;
    }

    /**
     * 跳转到分类表单页面
     *
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String contentForm(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }


    /**
     * 保存与新增用户
     *
     * @param tbContentCategory
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);

        if (baseResult.getStatus() == BaseResult.SUCCESS_STATUS) {
//            保存成功
            redirectAttributes.addFlashAttribute("result", baseResult);
            return "redirect:/content/category/list";
        } else {
//            保存失败
            model.addAttribute("result", baseResult);
            model.addAttribute("tbContentCategory", tbContentCategory);
            return "content_category_form";
        }
    }

}
