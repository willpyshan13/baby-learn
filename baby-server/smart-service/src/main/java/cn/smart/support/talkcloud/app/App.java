package cn.smart.support.talkcloud.app;//package cn.smart.support.talkcloud.app;

import java.util.HashMap;
import java.util.Map;

//
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

///**
// * 版本2.0.0
// * @author Administrator
// *
// */
public class App {
//
//    private static String Url = "http://global.talk-cloud.net/WebAPI/";
//    //		private static String Url = "http://demo.talk-cloud.net/WebAPI/";
//    //		private static String Url = "http://global.talk-cloud.neiwang/WebAPI/";//必须试一下https的
//
//    //	private static String pwdUrl = "http://global.talk-cloud.net:81/Example/";   //密码测试地址
//    private static String pwdUrl = "http://demo.talk-cloud.net/Example/";
//
//    private static String Key = "yil97lLwpd6uELjB";
//    private static int count = 0;
//    private static String serial = "772152415";//课堂号
//    private static String liveserial = "608914470"; //直播号
//    private static Map<String,String> errorDate = new HashMap<>();
//    private static MD5Util md5 = null;
//    public static void main(String[] args) {
//
//        /**0
//         * 备注：
//         * roomdelete删除房间必须每次都得找一个存在的房间；
//         * getroomchat聊天接口现在没有数据，
//         * getrecordlist内网的录制关闭了；
//         */
//        md5 = new MD5Util();
//        roomcreate();//预约房间
//        //								roommodify();//修改房间
//        //								roomdelete();//删除房间
//        //				getroomlist();//得到房间列表
//        //				getroombytime();//得到某个时间范围内的房间
//        //				getroom();//得到某个房间的详细信息
//        //				uploadfile();//上传文档？
//        //				roombindfile();//房间关联文档
//        //		roomonlinenum(); //获取房间在线用户数
//        //		getlogininfo();//获取房间用户登入登出情况
//        //		getonlineuser();//获取房间当前在线用户信息
//        //		getroomfile();//获取房间的文档列表
//        //		getrecordlist();//获取房间的录制件列表
//        //		getusergift();//获取房间的用户奖章或礼物
//        //		getroomchat();//获取房间的聊天信息    15个
//        //		roomdeletefile();//取消关联文档
//        //						deletefile();//删除课件
//        //						getfileconvertstatus();//查询文件状态
//
////		getserverarea();
//        //		entry();//测试https和http
//        //				ex_aestest();
//
//        /**
//         * 直播接口
//         *
//         */
//        //		channelcreate();//预约直播
//        //		channelmodify();//修改直播
//        //		channeldelete();//删除直播
//        //		getchannelonlineuser();//获取直播当前在线用户信息
//        //		channelonlinenum();//获取直播在线用户数
//        /**
//         * 创建子企业
//         */
//        //						companycreate();//创建企业
//        //										companyattribute();//设置企业属性
//        //								companydelete();//删除企业
//    }
//    //1.房间部分
//    //1.1创建房间
//    public static void roomcreate()
//    {
//        List<ParamsBean> list = new ArrayList<>();
//        String url = Url + "roomcreate/";
//        String roomName = md5.encode("接口测试一对多&&&");
//        list.add(new ParamsBean("key",Key)); //必填 企业id  authkey
//        list.add(new ParamsBean("roomname",roomName)); //必填 房间名称必填；如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
//        list.add(new ParamsBean("roomtype", 3));//0:1对1   3：1对多
//        int starts =time(); //当前时间戳
//        int ends = starts+24*3600;
//        list.add(new ParamsBean("starttime",starts)); //必填 房间开始时间戳(精确到秒)
//        list.add(new ParamsBean("endtime",ends)); //必填 房间结束时间(精确到秒)
//        list.add(new ParamsBean("chairmanpwd","t123456")); //必填 老师密码	必填，4=<长度<=16
//        list.add(new ParamsBean("assistantpwd", "2222"));//必填，助教密码4=<长度<=16
//        list.add(new ParamsBean("patrolpwd", "5555"));//必填，巡课密码4=<长度<=16
//        list.add(new ParamsBean("passwordrequired","0")); //选填 学生进入房间是否需要密码  0:否、1:是
//        list.add(new ParamsBean("confuserpwd",""));  //学生密码 passwordrequired = 1 时必填(4=<长度<=16)或者allowsidelineuser = 1 时必填
//        list.add(new ParamsBean("videotype","1"));  //选填 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
//        list.add(new ParamsBean("videoframerate",10));  //帧率10,15,20,25,30
//        list.add(new ParamsBean("autoopenav",1));//0: 不自动开启  1：自动开启
//        //		list.add(new ParamsBean("thirdroomid", "222111"));//这个是只有哒哒用
//
//        AtomicReference<String> r = null;
//
//        HttpUtils.httpSend(url, list, res -> {
//            System.out.println(res);
//            r.set(res);
//            testCount("roomcreate",res);
//        });
//    }
//    //修改房间
//    public static void roommodify()
//    {
//        String roomName = md5.encode("!@#$%^");
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "roommodify/";
//        list.add(new ParamsBean("key", Key));  //必填 企业id authkey
//        list.add(new ParamsBean("serial", serial)); //必填 房间号
//        list.add(new ParamsBean("roomname",roomName)); //必填 房间名
//        list.add(new ParamsBean("roomtype", 3));//0:一对一     3：一对多
//        int starts =time();  //当前时间戳
//        int ends = starts+24*3600;
//        list.add(new ParamsBean("starttime",starts)); //必填 房间开始时间戳(精确到秒)
//        list.add(new ParamsBean("endtime",ends)); //必填 房间结束时间(精确到秒)
//        list.add(new ParamsBean("chairmanpwd","t123456")); //必填 老师密码	必填，4=<长度<=16
//        list.add(new ParamsBean("assistantpwd", "a123456"));//必填，助教密码4=<长度<=16
//        list.add(new ParamsBean("patrolpwd", "p123456"));////必填，巡课密码4=<长度<=16
//        list.add(new ParamsBean("passwordrequired",1)); //选填 学生进入房间是否需要 0:否、1:是
//        list.add(new ParamsBean("confuserpwd","s123456")); //学生密码 passwordrequired = 1 时必填(4=<长度<=16)
//        list.add(new ParamsBean("videotype","0"));  //选填 视频分辨率 0：176x144   1：320x240  2：640x480   3：1280x720   4：1920x1080
//        list.add(new ParamsBean("videoframerate",10));  //帧率10,15,20,25,30
//        list.add(new ParamsBean("autoopenav",0));//0: 不自动开启  1：自动开启
//        HttpUtils.httpSend(url, list, new RequestCallback() {
//
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("roommodify",res);
//
//            }
//        });
//    }
//
//    //删除房间
//    public static void roomdelete()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "roomdelete/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial","1041167901"));//必填 房间号
//        HttpUtils.httpSend(url,list,new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("roomdelete",res);
//            }
//        });
//    }
//    //得到房间列表
//    public static void getroomlist()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getroomlist/";
//        list.add(new ParamsBean("key", Key)); //必填 企业id authkey
//        list.add(new ParamsBean("timetype", "1"));  //选填 房间类型 0:表示全部房间 1：表示当前房间 2：未来房间 3：历史房间
//        HttpUtils.httpSend(url, list, new RequestCallback() {
//
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getroomlist",res);
//            }
//        });
//    }
//
//
//
//    //得到某个时间范围内的房间
//    public static void getroombytime()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getroombytime/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        int starts =time(); //当前时间戳
//        int ends = starts+24*3600;
//        list.add(new ParamsBean("starttime",starts)); //必填 房间开始时间戳(精确到秒)
//        list.add(new ParamsBean("endtime",ends)); //必填 房间结束时间戳(精确到秒)
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getroombytime",res);
//            }
//        });
//
//    }
//    //得到某个房间的详细信息
//    public static void getroom()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getroom/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getroom",res);
//            }
//        });
//
//    }
    //上传文档
    private static void uploadfile()
    {
        //				String filepath="E:\\file\\课时4 FAF3U1L3动态ppt.pptx"; //必填 上传该文档的路径
        String filepath="C:\\Users\\Ye\\Desktop\\测试ppt.pptx"; //必填 上传该文档的路径
        //		String filepath="E:\\file\\邓超 - 无敌.mp3";
        String urlStr = "http://global.talk-cloud.net/WebAPI/" +"uploadfile";
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("filedata", filepath);
        Map<String, String> textMap = new HashMap<String, String>();
        textMap.put("key", "ACtb2LrFjgvQQdTz"); //必填 企业id authkey
//        textMap.put("serial", serial); //房间号
        textMap.put("conversion", "1"); //必填1：转换
        textMap.put("dynamicppt", "0");//是否是动态ppt   0: 非动态ppt   1: 动态ppt
        textMap.put("isopen", "1"); //选填 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
        textMap.put("isdefault","0");//是否是缺省显示文件0:不是  1：是    只有上传到房间的文件才需要设置。
        long i = System.currentTimeMillis();
        String ret = HttpUtils.formUpload(urlStr, textMap, fileMap);
        long j = System.currentTimeMillis();
        System.out.println(j - i);
        System.out.println(ret);
//        testCount("uploadfile",ret);
    }

    public static void main(String[] arg) {
        uploadfile();
    }

//    //房间关联文档
//    public static void roombindfile()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "roombindfile/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        list.add(new ParamsBean("fileid", 0));//选填，指定缺省显示文件的id .如果以前指定过，那么会重新指定为这个fileid.
//        list.add(new ParamsBean("fileidarr[]","	89672"));//必填 文件id组，文件id数组
//        list.add(new ParamsBean("fileidarr[]","89669"));//批量关联文档；
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("roombindfile",res);
//            }
//        });
//    }
//    //获取房间在线用户数
//    public static void roomonlinenum()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "roomonlinenum/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        list.add(new ParamsBean("type","0")); //选填 参加用户类型 0: 当前在线人数  1：登录人数
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("roomonlinenum",res);
//            }
//        });
//    }
//    //获取房间用户登入登出情况
//    public static void getlogininfo()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getlogininfo/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        //		list.add(new ParamsBean("", ));//
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getlogininfo",res);
//            }
//        });
//    }
//
//    //获取房间当前在线用户信息
//    public static void getonlineuser(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getonlineuser/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getonlineuser",res);
//            }
//        });
//    }
//
//    //获取房间的文档列表
//    public static void getroomfile()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getroomfile/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getroomfile",res);
//            }
//        });
//    }
//    //获取房间的录制件列表
//    public static void getrecordlist()
//    {
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getrecordlist/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getrecordlist",res);
//            }
//        });
//    }
//
//    //获取房间的用户奖章或礼物
//    public static void getusergift(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getusergift/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",serial)); //必填 房间号
//        //		list.add(new ParamsBean("userid","d144b8d9-4133-c833-9a70-4188bd26acda")); //用户id  receiveid
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getusergift",res);
//            }
//        });
//    }
//
//    //获取房间的聊天信息
//    public static void getroomchat(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getroomchat/";
//        list.add(new ParamsBean("key", Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial", serial));//必填 房间号
//        list.add(new ParamsBean("chattype", 0));  //类型  0:公聊;  2:私聊（目前只有0）
//        HttpUtils.httpSend(url, list, new RequestCallback() {
//
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getroomchat",res);
//            }
//        });
//    }
//    //取消关联文档
//    public static void roomdeletefile(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "roomdeletefile/";
//        list.add(new ParamsBean("key", Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial", serial));//必填 房间号
//        list.add(new ParamsBean("fileidarr[]","89677"));//必填 文件id组，文件id数组
//        //		list.add(new ParamsBean("fileidarr[]", "25570"));//批量取消关联文档；
//        HttpUtils.httpSend(url, list, new RequestCallback() {
//
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                //				testCount("roomdeletefile",res);
//            }
//        });
//    }
//    //删除课件
//    public static void deletefile(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "deletefile/";
//        list.add(new ParamsBean("key", Key)); //必填 企业id authkey
//        list.add(new ParamsBean("fileidarr[]","89677"));//必填，文件id
//        HttpUtils.httpSend(url, list, new RequestCallback() {
//
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getroomchat",res);
//            }
//        });
//
//    }
//
//    //查询文件状态
//    public static void getfileconvertstatus(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getfileconvertstatus/";
//        list.add(new ParamsBean("key", Key)); //必填 企业id authkey
//        list.add(new ParamsBean("fileid","120448"));//必填，文件id
//        HttpUtils.httpSend(url, list, new RequestCallback() {
//
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("getroomchat",res);
//            }
//        });
//    }
//
//    public static void getserverarea() {
//        String url = "http://global.talk-cloud.net/ClientAPI/getserverarea";
//        HttpUtils.httpSends(url, new RequestCallback() {
//
//            @Override
//            public void callBack(String res) {
//                System.out.println(res);
//            }
//        });
//    }
//
//    //2.登录部分
//    //创建以及加入房间：直接进入房间(auth没拼接好)
//    public static void entry()
//    {
//        int starts =time(); //当前时间戳
//        String keystr = Key; //必填 企业id authkey
//        String tsstr = starts + ""; //当前时间戳，不需要手动填
//        String serialstr = serial; //必填 房间号  非0开始的数字串， 请保证房间号唯一
//        String usertypestr = "2"; ////必填， 0：主讲(老师 )  1：助教    2: 学员   3：直播用户  4:巡检员  默认值为2；
//        String authstr = keystr + tsstr + serialstr + usertypestr;
//
//        String authmd5 = md5.MD5(authstr);
//        String pwd1 = "";//密码
//        String userpassword = null;
//        try {
//            userpassword = md5.encrypts(pwd1, keystr);
//            System.out.println("userpassword="+userpassword);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String userName = md5.encode("sdsf12");//#%&特殊字符不允许输入
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "entry/";
//        list.add(new ParamsBean("domain","test")); //公司域名
//        list.add(new ParamsBean("serial",serialstr)); //必填 房间号
//        list.add(new ParamsBean("username",userName)); //必填 用户名 用户在房间中显示的名称；使用UTF8编码，特殊字符需使用urlencode转义
//        list.add(new ParamsBean("usertype",usertypestr));//必填， 0：主讲(老师 )  1：助教    2: 学员   3：直播用户  4:巡检员  默认值为2；
//        list.add(new ParamsBean("pid", 0));//选填, 第三方系统的用户id；默认值为0
//        list.add(new ParamsBean("ts",tsstr)); //必填，当前GMT时间戳，int格式
//        list.add(new ParamsBean("auth",authmd5)); //必填，auth值为MD5(key + ts + serial + usertype)其中key为双方协商的接口密钥：默认值为：yil97lLwpd6uELjB
//        list.add(new ParamsBean("userpassword",userpassword)); //必填 用户密码  密码格式为：128位AES加密串加密密钥默认为5NIWjlgmvqwbt494注：参见附件5.1 AES加密
//        //		list.add(new ParamsBean("extradata", ""));//扩展数据  用户扩展数据，建议使用urlencode
//        list.add(new ParamsBean("servername", "cnb"));//cn/as/us
//        list.add(new ParamsBean("jumpurl", "http://www.global.talk-cloud.net"));//选填：课程结束课程后，自动跳转到通过参数传入的指定URL（进入时指定、不同角色不同）请在参数的最后面传递这个参数！
//
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                //				System.out.println(res);
//            }
//        });
//    }
//    //2.1密码测试  在登陆测试时，如果提示密码错误，可以使用本接口测试密码是否正确
//    private static void ex_aestest(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = pwdUrl + "ex_aestest/";
//        list.add(new ParamsBean("key", Key)); //必填 企业id authkey
//        list.add(new ParamsBean("pwd", "s123456"));//必填  原始密码
//        HttpUtils.httpSend(url, list, new RequestCallback() {
//
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                //				testCount("getroomchat",res);
//            }
//        });
//    }
//    //3.直播部分
//    //3.1预约直播
//    public static void channelcreate(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "channelcreate/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id  authkey
//        String roomName = md5.encode("111给v地方");
//        list.add(new ParamsBean("roomname",roomName)); //必填 直播名称  如果有中文请使用UTF8编码，不允许输入特殊字符
//        int starts =time(); //当前时间戳
//        int ends = starts+24*3600;
//        list.add(new ParamsBean("starttime",starts)); //必填开始时间戳(精确到秒)
//        list.add(new ParamsBean("endtime",ends)); //必填 结束时间(精确到秒)
//        list.add(new ParamsBean("chairmanpwd","1234567890123456")); //必填 老师密码	必填，4=<长度<=16
//        list.add(new ParamsBean("assistantpwd", "2222"));//必填，助教密码4=<长度<=16
//        list.add(new ParamsBean("patrolpwd", "4444"));//必填，巡课密码4=<长度<=16
//        list.add(new ParamsBean("sidelineuserpwd",""));  //围观学生密码 (4=<长度<=16)
//        list.add(new ParamsBean("Videotype","0"));  //视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
//        list.add(new ParamsBean("videoframerate",10));  //帧率10,15,20,25,30
//        list.add(new ParamsBean("robotnum", 4));//机器人数量  选填
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("channelcreate",res);
//            }
//        });
//
//    }
//    //3.2修改直播
//    public static void channelmodify(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "channelmodify/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id  authkey
//        list.add(new ParamsBean("serial", liveserial));//必填
//        String roomName = md5.encode("直播bhhj课堂");
//        list.add(new ParamsBean("roomname",roomName)); //必填 直播名称   如果有中文请使用UTF8编码，不允许输入特殊字符
//        int starts =time(); //当前时间戳
//        int ends = starts+24*3600;
//        list.add(new ParamsBean("starttime",starts)); //必填开始时间戳(精确到秒)
//        list.add(new ParamsBean("endtime",ends)); //必填 结束时间(精确到秒)
//        list.add(new ParamsBean("chairmanpwd","teacher123456")); //必填 老师密码	必填，4=<长度<=16
//        list.add(new ParamsBean("assistantpwd", "zhujiao123456"));//必填，助教密码4=<长度<=16
//        list.add(new ParamsBean("patrolpwd", "xunke123456"));//必填，巡课密码4=<长度<=16
//        list.add(new ParamsBean("sidelineuserpwd",""));  //旁听用户密码 4=<长度<=16
//        list.add(new ParamsBean("videotype","4"));  // 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
//        list.add(new ParamsBean("videoframerate",20));  //帧率10,15,20,25,30
//        list.add(new ParamsBean("robotnum", 10));//机器人数量
//        HttpUtils.httpSend(url, list, new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("channelmodify",res);
//            }
//        });
//    }
//    //3.3删除直播
//    public static void channeldelete(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "channeldelete/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial","920171657"));//必填 房间号
//        HttpUtils.httpSend(url,list,new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("channeldelete",res);
//            }
//        });
//    }
//
//    //3.4获取直播当前在线用户信息
//    public static void getchannelonlineuser(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "getchannelonlineuser/";
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",liveserial));//必填 房间号
//        HttpUtils.httpSend(url,list,new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                //				testCount("getchannelonlineuser",res);
//            }
//        });
//    }
//
//    //3.5获取直播某时间段的在线用户数
//    public static void channelonlinenum(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "channelonlinenum/";
//        int starts =time(); //当前时间戳
//        int ends = starts+24*3600;
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("serial",liveserial));//必填 房间号
//        list.add(new ParamsBean("starttime", starts));//当前时间
//        list.add(new ParamsBean("endtime", ends));//结束时间
//        HttpUtils.httpSend(url,list,new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                //				testCount("channelonlinenum",res);
//            }
//        });
//    }
//
//    //4.企业部分
//    //4.1创建企业
//    public static void companycreate(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "companycreate/";
//        String adminName= md5.encode("管理员");
//        String companyname = md5.encode("我的第2个企业");
//        list.add(new ParamsBean("key",Key)); //必填 企业id authkey
//        list.add(new ParamsBean("companyname",companyname));//必填 子企业名     如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
//        list.add(new ParamsBean("companydomain", "mysecond111"));//必填 子企业域名   只允许字母和数字组合
//        list.add(new ParamsBean("account", "admin"));//必填  子企业管理员帐号   只允许字母和数字组合
//        list.add(new ParamsBean("username", adminName));//必填  子企业管理员名字   如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
//        list.add(new ParamsBean("userpassword", "1111"));//必填  子企业管理员密码 4=<长度<=20
//        HttpUtils.httpSend(url,list,new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("companycreate",res);
//            }
//        });
//    }
//
//    //4.2设置企业属性
//    public static void companyattribute(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "companyattribute/";
//        String companyname = md5.encode("秦小末&&**");
//        list.add(new ParamsBean("key","ByTK4DDLgdFdiSiE")); //必填 企业id authkey
//        list.add(new ParamsBean("roomstartcallbackurl", "1"));//上课回调地址
//        list.add(new ParamsBean("roomendcallbackurl", "2"));//下课回调地址
//        list.add(new ParamsBean("roomloginouturl", "3"));//登入登出回调地址
//        list.add(new ParamsBean("updateflag", "0"));//回调地址同时更新子企业0：不更新子企业回调地址1：同时更新子企业回调地址
//        list.add(new ParamsBean("companyname", companyname));//企业名称  如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
//        list.add(new ParamsBean("companyico",""));//企业logo
//        HttpUtils.httpSend(url,list,new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("companyattribute",res);
//            }
//        });
//    }
//
//    //4.3删除企业
//    public static void companydelete(){
//        List<ParamsBean> list = new ArrayList<ParamsBean>();
//        String url = Url + "companydelete/";
//        list.add(new ParamsBean("key","ByTK4DDLgdFdiSiE")); //必填 企业id authkey
//        list.add(new ParamsBean("companydomain", "mysecond111"));//必填 子企业域名
//        HttpUtils.httpSend(url,list,new RequestCallback()
//        {
//            public void callBack(String res)
//            {
//                System.out.println(res);
//                testCount("companydelete",res);
//            }
//        });
//        System.out.println(errorDate.toString());
//    }
//
//
//    //获取当前时间戳 精确到秒
//    public static int time() {
//        int a = (int) (System.currentTimeMillis() / 1000.0);
//        return a;
//    }
//    //接口测试
//    public static void testCount(String Interface,String str){
//        JSONObject object = JSONObject.parseObject(str);
//        String value= object.getString("result");
//        if(value.equals("0")){
//            count++;
//            System.out.println("count="+count);
//        }else{
//            //			System.out.println("接口="+Interface+":"+value);
//            errorDate.put(Interface, value);
//        }
//    }
}
//
//
//
//
