package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.AppQueryCondition;
import cn.smart.entity.AppDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-5-15 9:28:36
 */
@Repository
public interface IAppMapper extends IBaseDao<AppDO, AppQueryCondition> {

}
