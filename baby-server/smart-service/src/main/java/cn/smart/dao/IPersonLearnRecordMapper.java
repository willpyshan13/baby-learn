package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PersonLearnRecordQueryCondition;
import cn.smart.entity.PersonLearnRecordDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-6-22 16:34:32
 */
@Repository
public interface IPersonLearnRecordMapper extends IBaseDao<PersonLearnRecordDO, PersonLearnRecordQueryCondition> {

}
