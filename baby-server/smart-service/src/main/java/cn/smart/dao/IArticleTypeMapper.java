package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ArticleTypeQueryCondition;
import cn.smart.entity.ArticleTypeDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-7-16 19:28:06
 */
@Repository
public interface IArticleTypeMapper extends IBaseDao<ArticleTypeDO, ArticleTypeQueryCondition> {

}
