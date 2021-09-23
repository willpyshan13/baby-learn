package cn.smart.common.base.service;

import cn.smart.common.base.entity.Entity;
import cn.smart.common.utils.QueryCondition;

import java.util.List;
import java.util.Map;

public interface ICrudService<T extends Entity,M extends QueryCondition> {


    void save(T t);

    void save(Map<String, Object> map);

    void saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int deleteById(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] ids);

     T queryById(Object id);

    List<T> queryList(M m);

    int queryTotal(M m);

    int total();

}
