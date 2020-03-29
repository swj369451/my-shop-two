package com.sm.my.shop.two.web.api.web.controller.v1;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.domain.TbContent;
import com.sm.my.shop.two.web.api.service.TbContentService;
import com.sm.my.shop.two.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//继承controller且返回的都是json
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent tbContent(Long id) {
        if (id != null) {
            return tbContentService.findById(id);
        }
        return new TbContent();
    }

    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult getTbContentByCategoryId(
            @PathVariable(value = "category_id") Long categoryId) {

        List<TbContent> tbContents = tbContentService.findByCategoryId(categoryId);
        List<TbContentDTO> tbContentDTOS = null;
        if (tbContents != null && tbContents.size() > 0) {
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO tbContentDTO = new TbContentDTO();
                BeanUtils.copyProperties(tbContent, tbContentDTO);
                tbContentDTOS.add(tbContentDTO);
            }
        }
        return BaseResult.success("成功",tbContentDTOS);
    }
}
