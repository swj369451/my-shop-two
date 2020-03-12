package com.sm.my.shop.two.commons.persistence;

import com.sm.my.shop.two.commons.dto.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public T findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void deleteMulti(String[] ids) {
        dao.deleteMultiByIds(ids);
    }

    @Override
    public Integer count(T entity) {
        return dao.count(entity);
    }

    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        PageInfo<T> baseEntityPageInfo = new PageInfo<>();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("start", start);
        parameter.put("length", length);
        parameter.put("pageParams", entity);

        Integer count = count(entity);
//        配置返回的结果
        baseEntityPageInfo.setDraw(draw);
        baseEntityPageInfo.setRecordsFiltered(count);
        baseEntityPageInfo.setRecordsTotal(count);
        baseEntityPageInfo.setData(dao.pageByLimit(parameter));
        return baseEntityPageInfo;
    }

}
