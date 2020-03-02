package com.sm.my.shop.two.commons.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {
    //    id
    private Long id;
    //    创建时间
    private Date created;
    //    更新时间
    private Date updated;


}
