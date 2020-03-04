package com.sm.my.shop.two.web.admin.service;

import com.sm.my.shop.two.commons.dto.BaseResult;
import com.sm.my.shop.two.commons.dto.PageInfo;
import com.sm.my.shop.two.domain.TbContent;

import java.util.List;
import java.util.Map;

public interface TbContentService {
    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);
    /**
     * 查询全部
     * @return
     */
    List<TbContent> selectAll();

    /**
     * 插入
     * @param tbContent
     */
    BaseResult save(TbContent tbContent);

    /**
     * 删除
     * @param contentId
     */
    void delete(Long contentId);

    /**
     * 根据id查询
     * @param contentId
     * @return
     */
    TbContent findById(Long contentId);

    /**
     * 分页查询
     * @param parameter
     * @return
     */
    List<TbContent> pageByLimit(Map<String,Object> parameter);

    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 总记录数
     * @return
     */
    Integer count(TbContent tbContent);
}
