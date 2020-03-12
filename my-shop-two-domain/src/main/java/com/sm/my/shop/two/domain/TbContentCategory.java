package com.sm.my.shop.two.domain;

import com.sm.my.shop.two.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 目录分类
 */
@Data
public class TbContentCategory extends BaseEntity {

    @Length(min = 1,max = 20,message = "分类名称必须介于1到20位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private Boolean isParent;
//    父节点的id为0则为根目录
    private TbContentCategory parent;
}
