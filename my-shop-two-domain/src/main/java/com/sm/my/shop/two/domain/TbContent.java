package com.sm.my.shop.two.domain;

import com.sm.my.shop.two.commons.persistence.BaseEntity;
import lombok.Data;

@Data
public class TbContent extends BaseEntity {
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;

}
