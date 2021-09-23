package cn.smart.service;

import cn.smart.bo.RoomBo;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.base.service.ICrudService;
import cn.smart.common.utils.R;
import cn.smart.condition.RoomQueryCondition;
import cn.smart.entity.RoomDO;

/**
* @author Ye
* @date 2018-4-9 19:43:22
*/
public interface IRoomService extends ICrudService<RoomDO, RoomQueryCondition> {

    R updateRoom(RoomBo room);

    R saveRoom(RoomBo room);

    void assignedCourseware(RoomBo roomBo);

    void cancelRoomCourseware(Long roomId, Long[] roomCoursewareIds, Long[] coursewareIds);

    ApiResult getPersonRoomList(Long personId);
}