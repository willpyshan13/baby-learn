package cn.smart.support.talkcloud.business;

import cn.smart.common.ConfigConstant;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.Validator;
import cn.smart.entity.RoomDO;
import cn.smart.support.talkcloud.app.HttpUtils;
import cn.smart.support.talkcloud.app.ParamsBean;
import cn.smart.support.talkcloud.entity.RecordResult;
import cn.smart.support.talkcloud.utils.Methods;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ye
 */
@Component
public class RoomBusiness {

    /**
     * 预约房间(新增教室)
     * @param roomDO 参数
     * @return map 结果 {"version":1196235,"serial":1906113110,"result":0}>
     */
    public Map createRoom(RoomDO roomDO) {

        String url = ConfigConstant.getBaseURL(Methods.ROOM_CREATE);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id  authkey
        try {
            list.add(new ParamsBean("roomname", URLEncoder.encode(roomDO.getRoomName(), "UTF8"))); //必填 房间名称必填；如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (roomDO.getRoomType().equals(1)) {
            list.add(new ParamsBean("roomtype", 0));//0:1对1   3：1对多
        } else {
            list.add(new ParamsBean("roomtype",3));//0:1对1   3：1对多
        }
        if (roomDO.getStartTime() != null) {
            Date startTime = DateUtils.CSTConvert(roomDO.getStartTime().toString());
            if (startTime != null) {
                list.add(new ParamsBean("starttime",startTime.getTime()/1000)); //必填 房间开始时间戳(精确到秒)
            }
        }
        if (roomDO.getEndTime() != null) {
            Date endTime = DateUtils.CSTConvert(roomDO.getEndTime().toString());
            if (endTime != null) {
                list.add(new ParamsBean("endtime",endTime.getTime()/1000)); //必填 房间结束时间(精确到秒)
            }
        }

        list.add(new ParamsBean("chairmanpwd", roomDO.getChairmanPwd())); //必填 老师密码	必填，4=<长度<=16
        list.add(new ParamsBean("assistantpwd", roomDO.getAssistantPwd()));//必填，助教密码4=<长度<=16
        list.add(new ParamsBean("patrolpwd", roomDO.getPatrolPwd()));//必填，巡课密码4=<长度<=16
        list.add(new ParamsBean("passwordrequired",roomDO.getPasswordRequired() == null ? 0 : roomDO.getPasswordRequired())); //选填 学生进入房间是否需要密码  0:否、1:是
        list.add(new ParamsBean("confuserpwd",roomDO.getConfuserPwd()));  //学生密码 passwordrequired = 1 时必填(4=<长度<=16)或者allowsidelineuser = 1 时必填
        list.add(new ParamsBean("videotype", roomDO.getVideoType() == null ? 0 : roomDO.getVideoType()));  //选填 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
        list.add(new ParamsBean("videoframerate", roomDO.getVideoFrameRate() == null ? 10 : roomDO.getVideoFrameRate()));  //帧率10,15,20,25,30
        list.add(new ParamsBean("autoopenav", roomDO.getAutoOpenAv() == null ? 0 : roomDO.getAutoOpenAv()));//0: 不自动开启  1：自动开启
        list.add(new ParamsBean("confusernum", roomDO.getConfuserNum())); // 课堂点数
        list.add(new ParamsBean("vcodec", roomDO.getVcodec() == null ? 0 : roomDO.getVcodec())); // h264硬件编码

        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);

    }


    public Map getRoomFile(Long roomId) {
        List<ParamsBean> list = new ArrayList<>();
        String url = ConfigConstant.getBaseURL(Methods.GET_ROOM_FILE);

        list.add(new ParamsBean("key", ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("serial", roomId)); //必填 房间号

        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);
    }


    /**
     * 教室关联课件(文档)
     * @param roomId 教室id
     * @param defaultId 默认课件id
     * @param ids 课件id 集合
     * @return
     */
    public Map roomBindFile(Long roomId, Long defaultId, Long[] ids) {

        String url = ConfigConstant.getBaseURL(Methods.ROOM_BIND_FILE);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("serial",roomId)); //必填 房间号
        if (!Validator.isEmpty(defaultId)) {
            list.add(new ParamsBean("fileid", defaultId));//选填，指定缺省显示文件的id .如果以前指定过，那么会重新指定为这个fileid.
        }
        for(Long id : ids) {
            list.add(new ParamsBean("fileidarr[]", id));//批量关联文档；
        }
        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);

    }

    /**
     * 取消关联文档
     * @param roomId 教室id
     * @param ids 文档id 数组
     * @return
     */
    public Map roomDeleteFile(Long roomId, Long[] ids) {

        String url = ConfigConstant.getBaseURL(Methods.ROOM_DELETE_FILE);

        List<ParamsBean> list = new ArrayList<ParamsBean>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("serial", roomId));//必填 房间号
        list.add(new ParamsBean("fileidarr[]", StringUtils.join(ids, ",")));//批量关联文档；

        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);

    }

    /**
     * 修改教室信息
     * @param roomDO 教室对象
     * @return ：{"result":0,"serial":"房间号"}
     */
    public Map modifyRoom(RoomDO roomDO) {

        String url = ConfigConstant.getBaseURL(Methods.ROOM_MODIFY);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id  authkey
        list.add(new ParamsBean("serial", roomDO.getId())); //必填 房间号
        try {
            list.add(new ParamsBean("roomname", URLEncoder.encode(roomDO.getRoomName(), "UTF8"))); //必填 房间名称必填；如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (roomDO.getRoomType().equals(1)) {
            list.add(new ParamsBean("roomtype", 0));//0:1对1   3：1对多
        } else {
            list.add(new ParamsBean("roomtype",3));//0:1对1   3：1对多
        }

        if (roomDO.getStartTime() != null) {
            Date startTime = DateUtils.CSTConvert(roomDO.getStartTime().toString());
            if (startTime != null) {
                list.add(new ParamsBean("starttime",startTime.getTime()/1000)); //必填 房间开始时间戳(精确到秒)
            }
        }
        if (roomDO.getEndTime() != null) {
            Date endTime = DateUtils.CSTConvert(roomDO.getEndTime().toString());
            if (endTime != null) {
                list.add(new ParamsBean("endtime",endTime.getTime()/1000)); //必填 房间结束时间(精确到秒)
            }
        }

        list.add(new ParamsBean("chairmanpwd", roomDO.getChairmanPwd())); //必填 老师密码	必填，4=<长度<=16
        list.add(new ParamsBean("assistantpwd", roomDO.getAssistantPwd()));//必填，助教密码4=<长度<=16
        list.add(new ParamsBean("patrolpwd", roomDO.getPatrolPwd()));//必填，巡课密码4=<长度<=16
        list.add(new ParamsBean("passwordrequired",roomDO.getPasswordRequired() == null ? 0 : roomDO.getPasswordRequired())); //选填 学生进入房间是否需要密码  0:否、1:是
        list.add(new ParamsBean("confuserpwd",roomDO.getConfuserPwd()));  //学生密码 passwordrequired = 1 时必填(4=<长度<=16)或者allowsidelineuser = 1 时必填
        list.add(new ParamsBean("videotype", roomDO.getVideoType() == null ? 0 : roomDO.getVideoType()));  //选填 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
        list.add(new ParamsBean("videoframerate", roomDO.getVideoFrameRate() == null ? 10 : roomDO.getVideoFrameRate()));  //帧率10,15,20,25,30
        list.add(new ParamsBean("autoopenav", roomDO.getAutoOpenAv() == null ? 0 : roomDO.getAutoOpenAv()));//0: 不自动开启  1：自动开启
        if (Validator.isNotEmpty(roomDO.getConfuserNum())) {
            list.add(new ParamsBean("confusernum", roomDO.getConfuserNum())); // 课堂点数
        }
        list.add(new ParamsBean("vcodec", roomDO.getVcodec() == null ? 0 : roomDO.getVcodec())); // h264硬件编码


        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);
    }

    /**
     * 获取某个教室的录制件列表
     * @param id 教室id
     * @return map
     */
    public Map getRecordList(Integer id) {

        String url = ConfigConstant.getBaseURL(Methods.GET_RECORD_LIST);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("serial",id)); //必填 房间号
        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);

    }

    /**
     * 获取某个教室的录制件列表
     * @param id 教室id
     * @return map
     */
    public RecordResult getRoomRecordList(Integer id) {

        String url = ConfigConstant.getBaseURL(Methods.GET_RECORD_LIST);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("serial",id)); //必填 房间号
        String result = HttpUtils.httpSend(url, list);

        return JSONObject.parseObject(result, RecordResult.class);

    }

    /**
     * 得到某个教室的详细信息
     * @param id 教室号
     * @return {"result":0,"serial":"856362573","roomname":"test","starttime":"1429261807","endtime":"1529261807","chairmanpwd":"123456",
     *              "assistantpwd":"7923","confuserpwd":"111111","sidelineuserpwd":"222222"}
     */
    public Map getRoomBySerial(Integer id) {

        String url = ConfigConstant.getBaseURL(Methods.GET_ROOM);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("serial",id)); //必填 房间号
        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);

    }

    /**
     * 删除教室
     * @param id 教室id
     * @return
     */
    public Map roomDelete(Integer id) {

        String url = ConfigConstant.getBaseURL(Methods.ROOM_DELETE);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key",ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("serial",id));//必填 房间号
        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);

    }
}
