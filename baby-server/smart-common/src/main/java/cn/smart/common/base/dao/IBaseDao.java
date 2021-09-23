package cn.smart.common.base.dao;

import cn.smart.common.base.entity.Entity;
import cn.smart.common.utils.QueryCondition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IBaseDao<T extends Entity, M extends QueryCondition> {

    void save(T t);

    void save(Map<String, Object> map);

    void insertSelective(T t);

    void saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int updateAll(T t);

    int deleteById(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    T queryById(Object id);

    List<T> queryList(M m);

    int queryTotal(M m);

    int total();
}
