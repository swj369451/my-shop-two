package com.sm.my.shop.two.web.api.service;

import com.sm.my.shop.two.domain.TbContent;

import java.util.List;


public interface TbContentService {

    List<TbContent> findByCategoryId(Long categoryId);

    TbContent findById(Long id);
}
