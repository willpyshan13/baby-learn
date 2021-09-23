package cn.smart.controller;


import cn.smart.bo.CoursewareBO;
import cn.smart.bo.RoomBo;
import cn.smart.common.annotation.Logs;
import cn.smart.common.exception.CustomException;
import cn.smart.common.exception.FormatException;
import cn.smart.common.utils.MyCheckUtils;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.CoursewareQueryCondition;
import cn.smart.condition.RoomQueryCondition;
import cn.smart.entity.RoomDO;
import cn.smart.service.ICoursewareService;
import cn.smart.service.IPushService;
import cn.smart.service.IRoomService;
import cn.smart.support.talkcloud.business.RoomBusiness;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 班级相关
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    private final IRoomService roomService;

    @Autowired
    private ICoursewareService coursewareService;

    @Autowired
    private RoomBusiness roomBusiness;

    @Autowired
    private IPushService pushService;

    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * 获取班级列表
     * @param roomQueryCondition 查询条件
     * @return R
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(RoomQueryCondition roomQueryCondition) {

        List<RoomDO> roomList = roomService.queryList(roomQueryCondition);
        int total = roomService.queryTotal(roomQueryCondition);

        PageUtils pageUtils = new PageUtils(roomList, total, roomQueryCondition.getLimit(), roomQueryCondition.getPage());

        return R.ok().put("roomList", pageUtils);
    }

    @GetMapping("/detail")
    @ResponseBody
    public R getRoomDetailById(@RequestParam("id") Long id) throws UnsupportedEncodingException {

        RoomDO room = roomService.queryById(id);

        return R.ok().put("room", room);
    }

    @GetMapping("/recordings")
    @ResponseBody
    public R getRoomRecordings(@RequestParam("roomId") Long roomId) {

        if (Validator.isEmpty(roomId)) {
            return R.error("教室编号不能为空");
        }

        Map map = roomBusiness.getRecordList(roomId.intValue());

        return R.ok().put("recordings", map.get("recordlist"));
    }



    /**
     * 新增教室
     * @param room 教室信息
     * @return R
     */
    @PostMapping("/add")
    @ResponseBody
    @Logs(value = "新增教室")
    public R add(@RequestBody RoomBo room) throws ParseException {

        try {
            MyCheckUtils.validate(room.getRoom());
        } catch (FormatException e) {
            e.printStackTrace();
            e.getCause().getMessage();
            return R.error(-1, e.getCause().getMessage());
        }
        return roomService.saveRoom(room);
    }

    /**
     * 更新教室
     * @param room 教室信息
     * @return R
     */
    @PostMapping("/update")
    @ResponseBody
    @Logs(value = "更新教室")
    public R update(@RequestBody RoomBo room) {

        try{
            return roomService.updateRoom(room);
        } catch (CustomException e) {
            return R.error(e.getMsg());
        }
    }

    /**
     * 教室课件列表
     * @param coursewareQueryCondition 查询信息
     * @return R
     */
    @GetMapping("/courseware")
    @ResponseBody
    public R list(CoursewareQueryCondition coursewareQueryCondition) {

        List<CoursewareBO> coursewareDOList = coursewareService.queryCoursewareByRoom(coursewareQueryCondition);

        int total = coursewareService.countCoursewareByRoom(coursewareQueryCondition);

        PageUtils pageUtils = new PageUtils(coursewareDOList, total, coursewareQueryCondition.getLimit(), coursewareQueryCondition.getPage());

        return R.ok().put("coursewareList", pageUtils);
    }

    /**
     * 教室关联课件
     * @param roomBo roomBo
     * @return R
     */
    @PostMapping("/assigned/courseware")
    @ResponseBody
    @Logs(value = "教室关联课件")
    public R assignCourseware(@RequestBody RoomBo roomBo) {

        roomService.assignedCourseware(roomBo);

        return R.ok();
    }

    /**
     * 教室删除关联课件
     * @param map roomId 教室id  ids 数组
     * @return
     */
    @PostMapping("/cancel/courseware")
    @ResponseBody
    @Logs(value = "教室取关课件")
    public R cancelCourseware(@RequestBody Map<String, Object> map) {
        Long roomId = ((Integer) map.get("roomId")).longValue();
        Long[] roomCoursewareIds = JSONObject.parseObject(map.get("roomCoursewareIds").toString(), Long[].class) ;
        Long[] coursewareIds = JSONObject.parseObject(map.get("coursewareIds").toString(), Long[].class) ;
        roomService.cancelRoomCourseware(roomId, roomCoursewareIds, coursewareIds);
        return R.ok();
    }

    /**
     * 教室分配班级
     * @param roomId 教室id
     * @param classId 班级id
     * @return R
     */
    @PostMapping("/assigned/class")
    @ResponseBody
    @Logs(value = "教室分配班级")
    public R courseStatus(@RequestParam("roomId") Long roomId, @RequestParam("classId") Long classId) {

        if (Validator.isEmpty(roomId)) {
            return R.error(-1, "教室id不能为空!");
        }
        if (Validator.isEmpty(classId)) {
            return R.error("班级id不能为空!");
        }
        RoomDO roomDo = new RoomDO();
        roomDo.setId(roomId);
        roomDo.setClassId(classId);
        roomService.update(roomDo);

        return R.ok("操作成功!");
    }

    @PostMapping("/deleteById")
    @ResponseBody
    @Logs(value = "删除教室")
    public R delete(@RequestParam("id") Long id) {
        if(Validator.isEmpty(id)) {
            return R.error(-1, "教室id不能为空!");
        }
        roomService.deleteById(id);
        return R.ok("删除成功!");
    }

    @PostMapping("/push/startLearning")
    @ResponseBody
    @Logs(value = "教室手动推送开始上课消息")
    public R pushStartLearningMsg(@RequestParam("roomId") Long roomId) {

        return pushService.pushStartLearningMsg(roomId);
    }
}
