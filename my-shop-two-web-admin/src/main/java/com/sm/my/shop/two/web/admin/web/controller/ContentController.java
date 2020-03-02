package com.sm.my.shop.two.web.admin.web.controller;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.dto.PageInfo;
import com.sm.my.shop.two.domain.TbContent;
import com.sm.my.shop.two.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("content")
@Controller
public class ContentController {

    @Autowired
    private TbContentService tbContentService;

    /**
     * @ModelAttribute的特点
     * 1.在所有@RequestMapping注解方法调用前执行
     * 2.返回的对象自动放到model里面去,key可能是返回对象的首字母小写
     */
    @ModelAttribute
    public TbContent getTbContent(Long contentId){
        if(contentId == null){
            return new TbContent();
        }else {
            return tbContentService.findById(contentId);
        }

    }

    /**
     * 内容列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
//        DataTables会自动请求数据,所以以下俩句代码就不需要了
//        List<TbContent> tbContents = tbContentService.selectAll();
//        model.addAttribute("list",tbContents);
        return "contentList";
    }

    /**
     * 跳转到内容表单页面
     * @return
     */
    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String contentForm(){
        return "content_form";
    }


    /**
     * 保存与新增内容
     * @param tbContent
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(TbContent tbContent,
                       Model model,
                       RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbContentService.save(tbContent);

        if(baseResult.getStatus() == BaseResult.SUCCESS_STATUS){
//            保存成功
            redirectAttributes.addFlashAttribute("result",baseResult);
            return "redirect:/content/list";
        }else{
//            保存失败
            model.addAttribute("result",baseResult);
            model.addAttribute("tbContent",tbContent);
            return "content_form";
        }
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){
        Integer draw = Integer.valueOf(request.getParameter("draw"));
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));

        return tbContentService.page(start, length, draw,tbContent);
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(){
        return "content_detail";
    }
}
