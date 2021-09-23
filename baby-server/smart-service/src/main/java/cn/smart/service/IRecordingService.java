package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.RecordingQueryCondition;
import cn.smart.entity.RecordingDO;

/**
* @author Ye
* @date 2018-10-12 13:44:22
*/
public interface IRecordingService extends ICrudService<RecordingDO, RecordingQueryCondition>{

    void saveSelective(RecordingDO recording);
}