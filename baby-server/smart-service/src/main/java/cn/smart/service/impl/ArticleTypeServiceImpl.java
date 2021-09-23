package cn.smart.service.impl;

import cn.smart.condition.ArticleTypeQueryCondition;
import cn.smart.dao.IArticleTypeMapper;
import cn.smart.entity.ArticleTypeDO;
import cn.smart.service.IArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-7-16 19:28:06
*/

@Service
public class ArticleTypeServiceImpl  implements IArticleTypeService {

    @Autowired
    private IArticleTypeMapper articleTypeMapper;

    @Override
    public void save(ArticleTypeDO articleTypeDO) {
        articleTypeMapper.save(articleTypeDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        articleTypeMapper.save(map);
    }

    @Override
    public void saveBatch(List<ArticleTypeDO> list) {
        articleTypeMapper.saveBatch(list);
    }

    @Override
    public int update(ArticleTypeDO articleTypeDO) {
        return articleTypeMapper.update(articleTypeDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return articleTypeMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return articleTypeMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return articleTypeMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return articleTypeMapper.deleteBatch(ids);
    }

    @Override
    public ArticleTypeDO queryById(Object id) {
        return articleTypeMapper.queryById(id);
    }

    @Override
    public List<ArticleTypeDO> queryList(ArticleTypeQueryCondition articleTypeQueryCondition) {
        return articleTypeMapper.queryList(articleTypeQueryCondition);
    }

    @Override
    public int queryTotal(ArticleTypeQueryCondition articleTypeQueryCondition) {
        return articleTypeMapper.queryTotal(articleTypeQueryCondition);
    }

    @Override
    public int total() {
        return articleTypeMapper.total();
    }
}
