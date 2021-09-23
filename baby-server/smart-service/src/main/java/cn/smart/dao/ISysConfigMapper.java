package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.SysConfigQueryCondition;
import cn.smart.entity.SysConfigDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-5-17 10:14:25
 */
@Repository
public interface ISysConfigMapper extends IBaseDao<SysConfigDO, SysConfigQueryCondition> {

}
