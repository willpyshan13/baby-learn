package cn.smart.common.api.result;

public interface ApiStatus {

    /**
     * token 失效
     */
    int SC_TOKEN_INVALID = 4000;
    /**
     * 验证码为空
     */
    int SC_VERIFY_CODE_NULL = 4001;
    /**
     * 手机号码为空
     */
    int SC_MOBILE_PHONE_NULL = 4002;
    /**
     * 验证码已发送
     */
    int SC_VERIFY_CODE_SENT = 4003;
    /**
     * 手机号码已注册
     */
    int SC_MOBILE_PHONE_REGISTERED = 4004;
    /**
     * 验证码错误
     */
    int SC_VERIFY_CODE_ERROR = 4005;
    /**
     * 验证码无效
     */
    int SC_VERIFY_CODE_INVALID = 4006;
    /**
     * 密码为空
     */
    int SC_PASSWORD_NULL = 4007;
    /**
     * 课程id为空
     */
    int SC_COURSE_STAGE_ID_NULL = 4008;
    /**
     * 手机号码未注册
     */
    int SC_MOBILE_PHONE_UNREGISTERED = 4009;
    /**
     * 图片超果设定大小
     */
    int SC_IMAGE_SIZE_ERROR = 4010;
    /**
     * 用户不存在
     */
    int SC_USER_DOSE_NOT_EXIST = 4011;
    /**
     * token 为空
     */
    int SC_TOKEN_NULL = 4012;
    /**
     * 用户推送标记为空
     */
    int SC_PUSH_MARK_NULL = 4013;
    /**
     * 用户名为空
     */
    int SC_USER_NAME_NULL = 4014;
    /**
     * 用户生日为空
     */
    int SC_BIRTHDAY_NULL = 4015;
    /**
     * 用户密码错误
     */
    int SC_PASSWORD_ERROR = 4016;

    /**
     * 验证码发送错误
     */
    int SC_VERIFY_CODE_SEND_ERROR = 5001;
    /**
     * 注销错误
     */
    int SC_LOG_OUT_ERROR = 5002;
    /**
     * 更新密码错误
     */
    int SC_UPDATE_PASSWORD_ERROR=5003;

    /**
     * 访问网页了类型 1:首页 2: 课程详情页
     */
    int SC_PAGEVIEWS_TYPE_NULL = 5004;

    /**
     * 访问网页地址
     */
    int SC_PAGEVIEWS_URL_NULL = 5005;

    /**
     * 访问设备序列号
     */
    int SC_PAGEVIEWS_SERIAL_NUMBER_NULL = 5006;

    /**
     * 访问渠道 1:pc 2:h5
     */
    int SC_PAGEVIEWS_CHANNEL_NULL = 5007;

    /**
     * 打卡日期
     */
    int CLOCK_DATE_IS_NULL = 5008;

    /**
     * 打卡日期错误
     */
    int CLOCK_DATE_INCORRECT = 5009;

    /**
     * 打卡日期存在
     */
    int CLOCK_DATE_EXISTS = 5010;
    /**
     * 复习积分已经存在
     */
    int REVIEW_POINTS_EXISTS = 5011;

    /**
     * 课程不存在
     */
    int SC_COURSE_IS_NULL = 5012;
    /**
     * 机器序列号不能为空
     */
    int SC_SERIAL_NUMBER_IS_NULL = 5013;
    /**
     * 当前版本号不能为空
     */
    int SC_CURRENT_VERSION_IS_NULL = 5014;
    /**
     * 积分不足
     */
    int SC_LACK_OF_INTEGRAL = 5015;
    /**
     * 兑换物品不存在
     */
    int SC_GOODS_IS_NULL = 5016;
    /**
     * 商品类型错误
     */
    int SC_GOODS_TYPE_ERROR = 5017;
    /**
     * 课程已经签到
     */
    int SC_COURSE_CLOCK_IN = 5018;
}
