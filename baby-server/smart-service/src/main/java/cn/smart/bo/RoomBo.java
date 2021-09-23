package cn.smart.bo;

import cn.smart.entity.RoomDO;

public class RoomBo {

    private Long roomId;

    private Long[] coursewareIds;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long[] getCoursewareIds() {
        return coursewareIds;
    }

    public void setCoursewareIds(Long[] coursewareIds) {
        this.coursewareIds = coursewareIds;
    }

    /**
     * 教室信息
     */
    private RoomDO room;

    public RoomDO getRoom() {
        return room;
    }

    public void setRoom(RoomDO room) {
        this.room = room;
    }

}
