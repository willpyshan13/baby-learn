package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.RoomCoursewareQueryCondition;
import cn.smart.entity.RoomCoursewareDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-25 19:38:39
 */
@Repository
public interface IRoomCoursewareMapper extends IBaseDao<RoomCoursewareDO, RoomCoursewareQueryCondition> {

}
