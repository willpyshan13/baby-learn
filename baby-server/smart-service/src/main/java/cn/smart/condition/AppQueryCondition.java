package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

/**
 * @author Ye
 * @date 2018-5-16 14:14:39
 */

public class AppQueryCondition extends QueryCondition {

    /**
     *
     */
    private Long appId;
    /**
     * app 类型 0:桌面 1:课程 2:直播 3:视频 4:个人中心,5:系统
     */
    private Integer appType;
    /**
     * 路径
     */
    private String path;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 版本信息
     */
    private String versionDesc;
    /**
     * 版本名称
     */
    private String versionName;
    /**
     * 版本号
     */
    private Double versionCode;


    /**
     * 是否开启白名单 0 否 1 是
     */
    private Integer white;

    /**
     *
     */
    public Long getAppId() {
        return appId;
    }

    /**
     *
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * app 类型 0:桌面 1:课程 2:直播 3:视频 4:个人中心
     */
    public Integer getAppType() {
        return appType;
    }

    /**
     * app 类型 0:桌面 1:课程 2:直播 3:视频 4:个人中心
     */
    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    /**
     * 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 版本信息
     */
    public String getVersionDesc() {
        return versionDesc;
    }

    /**
     * 版本信息
     */
    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    /**
     * 版本名称
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 版本名称
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * 版本号
     */
    public Double getVersionCode() {
        return versionCode;
    }

    /**
     * 版本号
     */
    public void setVersionCode(Double versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getWhite() {
        return white;
    }

    public void setWhite(Integer white) {
        this.white = white;
    }
}
