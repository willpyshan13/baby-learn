package cn.smart.bo;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 课件表
 * @author Ye
 * @date 2018-4-25 20:12:54
 */
public class CoursewareBO extends BaseEntity {

    /**
     *
     */
    private Long id;


    /**
     * 文件名称
     */
    private String name;


    /**
     * 文件类型1:视频 2:音频 3:文档
     */
    private Integer type;


    /**
     * 课件类型 0:本地上传
     */
    private Integer uploadType;


    /**
     * 课件地址
     */
    private String resourceUrl;


    /**
     * 文件属性 0: 非动态 ppt 1: 动态 ppt
     */
    private Integer dynamicPpt;


    /**
     * 上传者
     */
    private String creator;


    /**
     * 状态
     */
    private Integer status;


    /**
     * 文件大小
     */
    private Double size;


    /**
     * 默认课件 0:不是  1：是
     */
    private Integer isDefault;


    /**
     * 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
     */
    private Integer isOpen;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 更新时间(自动维护)
     */
    private Date updateTime;

    /**
     * 房间id
     */
    private Long roomId;

    /**
     *  房间名称
     */
    private String roomName;

    /**
     * 教室课件关联表id
     */
    private Long roomCoursewareId;

    /**
     * Get
     */
    public Long getId() {
        return id;
    }

    /**
     * Set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Get 文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * Set 文件名称
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get 文件类型1:视频 2:音频 3:文档
     */
    public Integer getType() {
        return type;
    }

    /**
     * Set 文件类型1:视频 2:音频 3:文档
     */
    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * Get 课件类型 0:本地上传
     */
    public Integer getUploadType() {
        return uploadType;
    }

    /**
     * Set 课件类型 0:本地上传
     */
    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }
    /**
     * Get 课件地址
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * Set 课件地址
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
    /**
     * Get 文件属性 0: 非动态 ppt 1: 动态 ppt
     */
    public Integer getDynamicPpt() {
        return dynamicPpt;
    }

    /**
     * Set 文件属性 0: 非动态 ppt 1: 动态 ppt
     */
    public void setDynamicPpt(Integer dynamicPpt) {
        this.dynamicPpt = dynamicPpt;
    }
    /**
     * Get 上传者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Set 上传者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    /**
     * Get 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Set 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * Get 文件大小
     */
    public Double getSize() {
        return size;
    }

    /**
     * Set 文件大小
     */
    public void setSize(Double size) {
        this.size = size;
    }
    /**
     * Get 默认课件 0:不是  1：是
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * Set 默认课件 0:不是  1：是
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
    /**
     * Get 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
     */
    public Integer getIsOpen() {
        return isOpen;
    }

    /**
     * Set 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
     */
    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }
    /**
     * Get 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Set 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * Get 更新时间(自动维护)
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Set 更新时间(自动维护)
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomCoursewareId() {
        return roomCoursewareId;
    }

    public void setRoomCoursewareId(Long roomCoursewareId) {
        this.roomCoursewareId = roomCoursewareId;
    }
}