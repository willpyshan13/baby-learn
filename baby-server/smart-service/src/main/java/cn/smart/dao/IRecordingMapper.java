package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.RecordingQueryCondition;
import cn.smart.entity.RecordingDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-10-12 16:44:42
 */
@Repository
public interface IRecordingMapper extends IBaseDao<RecordingDO, RecordingQueryCondition> {

}
