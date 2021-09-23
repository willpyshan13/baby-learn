package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ArticleQueryCondition;
import cn.smart.entity.ArticleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-7-16 19:11:49
 */
@Repository
public interface IArticleMapper extends IBaseDao<ArticleDO, ArticleQueryCondition> {

    List<ArticleDO> queryAllTypeArticle(ArticleQueryCondition query);

    List<ArticleDO> queryByTags(ArticleQueryCondition query);

    int queryTotalByTags(ArticleQueryCondition query);
}
