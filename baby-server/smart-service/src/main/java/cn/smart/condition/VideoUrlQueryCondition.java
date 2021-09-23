package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-5-2 9:49:46
 */

public class VideoUrlQueryCondition extends QueryCondition {

    /**
     * 视频id
     */
    private Long videoUrlId;

    /**
     * 查询没有关联的视频
     */
    private Boolean emptyVideoId;

    /**
     * 内容id
     */
    private Long videoId;
    /**
     * 视频格式
     */
    private String videoFormat;
    /**
     * 视频描述
     */
    private String videoUrlDesc;
    /**
     * 预览图
     */
    private String videoPlayUrl;
    /**
     * 视频地址
     */
    private String videoWebUrl;
    /**
     * 排序
     */
    private Integer videoUrlIndex;
    /**
     * 状态 0:生效 1:删除 2:不可用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间(自动维护)
     */
    private Date updateTime;

    /**
     * 视频id
     */
    public Long getVideoUrlId() {
        return videoUrlId;
    }

    /**
     * 视频id
     */
    public void setVideoUrlId(Long videoUrlId) {
        this.videoUrlId = videoUrlId;
    }

    /**
     * 内容id
     */
    public Long getVideoId() {
        return videoId;
    }

    /**
     * 内容id
     */
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    /**
     * 视频格式
     */
    public String getVideoFormat() {
        return videoFormat;
    }

    /**
     * 视频格式
     */
    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    /**
     * 视频描述
     */
    public String getVideoUrlDesc() {
        return videoUrlDesc;
    }

    /**
     * 视频描述
     */
    public void setVideoUrlDesc(String videoUrlDesc) {
        this.videoUrlDesc = videoUrlDesc;
    }

    /**
     * 预览图
     */
    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    /**
     * 预览图
     */
    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }

    /**
     * 视频地址
     */
    public String getVideoWebUrl() {
        return videoWebUrl;
    }

    /**
     * 视频地址
     */
    public void setVideoWebUrl(String videoWebUrl) {
        this.videoWebUrl = videoWebUrl;
    }

    /**
     * 排序
     */
    public Integer getVideoUrlIndex() {
        return videoUrlIndex;
    }

    /**
     * 排序
     */
    public void setVideoUrlIndex(Integer videoUrlIndex) {
        this.videoUrlIndex = videoUrlIndex;
    }

    /**
     * 状态 0:生效 1:删除 2:不可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 0:生效 1:删除 2:不可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间(自动维护)
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间(自动维护)
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEmptyVideoId() {
        return emptyVideoId;
    }

    public void setEmptyVideoId(Boolean emptyVideoId) {
        this.emptyVideoId = emptyVideoId;
    }
}
