package com.sm.my.shop.two.domain;

import com.sm.my.shop.two.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * 目录分类
 */
@Data
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private boolean isParent;
}
