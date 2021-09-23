package cn.smart.support.talkcloud.utils;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(3001, "服务器过期");
        map.put(3002, "公司已经冻结");
        map.put(3003, "房间已删除或过期");
        map.put(4001, "该公司不存在");
        map.put(4002, "用户名或密码错误");
        map.put(4003, "房间名称不允许为空");
        map.put(4004, "时间格式错误");
        map.put(4005, "时间设置有误");
        map.put(4006, "老师密码格式错误");
        map.put(4007, "房间不存在、房间无效已被删除、房间过期");
        map.put(4008, "房间密码错误");
        map.put(4009, "学生用户密码格式错误");
        map.put(4010, "密码格式错误");
        map.put(4011, "房间类型必填");
        map.put(4101, "需要验证企业 ID");
        map.put(4102, "验证企业 ID 错误");
        map.put(4103, "房间数超过最大限制");
        map.put(4105, "没有符合的数据");
        map.put(4106, "房间文件数量超过数量限制");
        map.put(4107, "数据库操作错误");
        map.put(4108, "没有权限 ");
        map.put(4109, "Auth 不正确");
        map.put(4110, "该房间需要密码，请输入密码");
        map.put(4111, "非注册用户或密码不正确，该房间要求必须注册用户才能参会");
        map.put(4200, "组 id 必填");
        map.put(4201, "组名必填");
        map.put(4202, "文件 id 必填");
        map.put(5000, "用户名密码必填");
        map.put(5001, "房间是否有密码必填");
        map.put(5002, "主席密码必填");
        map.put(5003, "房间普通密码必填");
        map.put(5004, "开始时间，结束时间必填");
        map.put(5005, "如果允许旁听用户则应有密码");
        map.put(5006, "房间学员密码格式错误");
        map.put(5007, "旁听用户密码错误");
        map.put(5008, "最大用户数格式错误");
        map.put(5009, "最大用户数超过限制");
        map.put(5010, "最大旁听用户数错误");
        map.put(5011, "最大旁听用户数超过限制");
        map.put(5012, "最大视频数格式错误");
        map.put(5013, "最大视频数错误");
        map.put(5014, "最大音频数格式错误");
        map.put(5015, "最大音频数错误");
        map.put(5016, "视频分辨率参数格式错误");
        map.put(5017, "布局参数格式错误");
        map.put(5018, "助教密码必填");
        map.put(5019, "巡课密码必填");
        map.put(5020, "视频分辨率错误或视频分辨率超过企业限制");
        map.put(5100, "需要房间编号");
        map.put(5101, "需要用户账号");
        map.put(5102, "房间密码不可重复");
        map.put(5200, "主席密码错误");
        map.put(5201, "旁听密码错误");
        map.put(5203, "学员密码");
        map.put(5204, "没有密码");
        map.put(5205, "昵称或名字必填");
        map.put(5206, "数据写入失败");
        map.put(5207, "用户类型  0：主讲(老师 )  1：助教    2: 学员   3：直播用户  4:巡检员  ");
        map.put(5208, "用户已登陆");
        map.put(5300, "登录 40 位 key 必填");
        map.put(5301, "40 位 key 错误");
        map.put(6000, "serverid 不正确");
        map.put(6001, "ip 地址不正确");
        map.put(6002, "普通用户数超出范围");
        map.put(6003, "旁听用户数超出范围");
        map.put(7001, "userid 必填");
        map.put(7002, "accout 必填");
        map.put(7003, "密码必填");
        map.put(7004, "部门 ID 必填");
        map.put(7005, "公司 ID 必填，缺省为 1");
        map.put(7006, "Accout 已经存在");
        map.put(7007, "用户账号不存在");
        map.put(8001, "部门 id 必填");
        map.put(8002, "部门名称必填");
        map.put(8003, "公司 ID 必填，缺省为 1");
        map.put(8004, "父部门 ID 必填");
        map.put(8005, "部门级别必填");
        map.put(9001, "公司 ID 必填");
        map.put(9002, "公司名必填");
        map.put(9003, "二级域名必填");
        map.put(9004, "公司管理员 ID");
        map.put(9006, "该公司已经存在");
        map.put(9007, "企业 ID 必填");
    }

    public static String getErrorMessage(Integer code) {
       return map.get(code);
    }
}
