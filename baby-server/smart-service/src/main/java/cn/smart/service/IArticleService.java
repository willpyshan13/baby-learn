package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.ArticleQueryCondition;
import cn.smart.entity.ArticleDO;

import java.util.List;

/**
* @author Ye
* @date 2018-7-16 19:11:49
*/
public interface IArticleService extends ICrudService<ArticleDO, ArticleQueryCondition> {

    List<ArticleDO> queryAllTypeArticle(ArticleQueryCondition query);

    List<ArticleDO> queryByTags(ArticleQueryCondition query);

    int queryTotalByTags(ArticleQueryCondition query);
}