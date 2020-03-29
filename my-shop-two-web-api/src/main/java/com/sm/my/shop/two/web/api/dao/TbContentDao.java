package com.sm.my.shop.two.web.api.dao;

import com.sm.my.shop.two.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentDao {

    List<TbContent> findByCategoryId(TbContent tbContent);
}
