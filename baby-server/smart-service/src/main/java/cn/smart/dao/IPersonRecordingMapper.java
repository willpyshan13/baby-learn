package cn.smart.dao;

import cn.smart.bo.RecordingBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PersonRecordingQueryCondition;
import cn.smart.entity.PersonRecordingDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-10-12 17:14:16
 */
@Repository
public interface IPersonRecordingMapper extends IBaseDao<PersonRecordingDO, PersonRecordingQueryCondition> {

    List<RecordingBO> queryPersonRecordingList(PersonRecordingQueryCondition prQuery);
}
