package cn.smart.service.impl;

import cn.smart.condition.ArticleQueryCondition;
import cn.smart.dao.IArticleMapper;
import cn.smart.entity.ArticleDO;
import cn.smart.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-7-16 19:11:49
*/

@Service
public class ArticleServiceImpl  implements IArticleService {

    @Autowired
    private IArticleMapper articleMapper;

    @Override
    public void save(ArticleDO articleDO) {
        articleMapper.save(articleDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        articleMapper.save(map);
    }

    @Override
    public void saveBatch(List<ArticleDO> list) {
        articleMapper.saveBatch(list);
    }

    @Override
    public int update(ArticleDO articleDO) {
        return articleMapper.update(articleDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return articleMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return articleMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return articleMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return articleMapper.deleteBatch(ids);
    }

    @Override
    public ArticleDO queryById(Object id) {

        ArticleDO article = articleMapper.queryById(id);
        if (article.getTags() != null) {
            String[] tagsArr = article.getTags().split(" ");
            ArrayList<String> tags = new ArrayList<>();
            for (String tag : tagsArr) {
                tags.add(new String(Base64.getDecoder().decode(tag)));
            }
            article.setTags(String.join("/", tags));
        }
        return article;
    }

    @Override
    public List<ArticleDO> queryList(ArticleQueryCondition articleQueryCondition) {
        return articleMapper.queryList(articleQueryCondition);
    }

    @Override
    public int queryTotal(ArticleQueryCondition articleQueryCondition) {
        return articleMapper.queryTotal(articleQueryCondition);
    }

    @Override
    public int total() {
        return articleMapper.total();
    }

    @Override
    public List<ArticleDO> queryAllTypeArticle(ArticleQueryCondition query) {
        return articleMapper.queryAllTypeArticle(query);
    }

    @Override
    public List<ArticleDO> queryByTags(ArticleQueryCondition query) {
        return articleMapper.queryByTags(query);
    }

    @Override
    public int queryTotalByTags(ArticleQueryCondition query) {
        return articleMapper.queryTotalByTags(query);
    }
}
