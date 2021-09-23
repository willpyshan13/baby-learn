package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.SysLogsQueryCondition;
import cn.smart.entity.SysLogsDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-8-23 18:41:26
 */
@Repository
public interface ISysLogsMapper extends IBaseDao<SysLogsDO, SysLogsQueryCondition> {

    void saveApiLog(SysLogsDO log);

    int updateApiLog(SysLogsDO log);

    List<SysLogsDO> queryApiLogList(SysLogsQueryCondition query);

    int countApiLog(SysLogsQueryCondition query);
}
