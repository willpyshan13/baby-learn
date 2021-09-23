package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.SysLogsQueryCondition;
import cn.smart.entity.SysLogsDO;

import java.util.List;

/**
* @author Ye
* @date 2018-8-23 18:41:26
*/
public interface ISysLogsService extends ICrudService<SysLogsDO, SysLogsQueryCondition>{

    void saveApiLog(SysLogsDO log);

    int updateApiLog(SysLogsDO log);

    List<SysLogsDO> queryApiLogList(SysLogsQueryCondition query);

    int countApiLog(SysLogsQueryCondition query);
}