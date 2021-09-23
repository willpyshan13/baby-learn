package cn.smart.support.talkcloud.utils;

public interface Methods {

    /**
     * 预约房间
     */
    String ROOM_CREATE = "roomcreate";

    /**
     * 删除房间
     */
    String ROOM_MODIFY = "roommodify";

    /**
     * 修改房间
     */
    String ROOM_DELETE = "roomdelete";

    /**
     * 得到房间列表
     */
    String GET_ROOM_LIST = "getroomlist";

    /**
     * 得到某个时间范围内的房间
     */
    String GET_ROOM_BY_TIME = "getroombytime";

    /**
     * 得到某个房间的详细信息
     */
    String GET_ROOM = "getroom";

    /**
     *  文档上传
     */
    String UPLOAD_FILE = "uploadfile";

    /**
     * 房间关联文档
     */
    String ROOM_BIND_FILE = "roombindfile";

    /**
     * 获取房间在线用户数
     */
    String ROOM_ONLINE_NUM = "roomonlinenum";

    /**
     * 获取房间用户登入登出情况
     */
    String GET_LOGIN_INFO = "getlogininfo";

    /**
     * 获取房间当前在线用户信息
     */
    String GET_ONLINE_USER = "getonlineuser";

    /**
     * 获取房间的文档列表
     */
    String GET_ROOM_FILE = "getroomfile";

    /**
     * 获取房间的录制件列表
     */
    String GET_RECORD_LIST = "getrecordlist";

    /**
     * 获取房间的用户奖章或礼物
     */
    String GET_USER_GIFT = "getusergift";

    /**
     * 获取房间的聊天信息
     */
    String GET_ROOM_CHAT = "getroomchat";

    /**
     * 取消关联文档
     */
    String ROOM_DELETE_FILE = "roomdeletefile";

    /**
     *  删除课件
     */
    String DELETE_FILE = "deletefile";

    /**
     * 查询文件状态
     */
    String GET_FILE_CONVERT_STATUS = "getfileconvertstatus";

    /**
     * 创建以及加入房间；直接进入房间
     */
    String ENTRY = "entry";

    /**
     * 密码测试
     */
    String EX_AESTEST = "ex_aestest";

    /**
     * 创建企业
     */
    String COMPANY_CREATE = "Companycreate";

    /**
     * 设置企业属性
     */
    String COMPANY_ATTRIBUTE = "companyattribute";

    /**
     * 删除企业
     */
    String COMPANY_DELETE = "companydelete";

}
