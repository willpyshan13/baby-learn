package cn.smart.dao;

import cn.smart.bo.RoomListBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.RoomQueryCondition;
import cn.smart.entity.RoomDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-10 14:09:01
 */
@Repository
public interface IRoomMapper extends IBaseDao<RoomDO, RoomQueryCondition> {

    List<RoomListBO> queryRoomByPersonId(Long id);

    void insertSelective(RoomDO roomDO);
}
