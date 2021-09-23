package cn.smart.bo;

import java.util.Date;

public class CourseGameBO {

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程游戏id
     */
    private Long courseGameId;

    /**
     * 游戏id
     */
    private Long gameId;

    /**
     * 游戏id集合
     */
    private Long[] gameIds;

    /**
     * 游戏名称
     */
    private String name;


    /**
     * 游戏信息
     */
    private String info;


    /**
     * 图片地址
     */
    private String imageUrl;


    /**
     * 资源地址
     */
    private String resourceUrl;


    /**
     * 状态 1 ：生效 0 ：失效
     */
    private Integer status;


    /**
     * 类型
     */
    private Integer type;


    /**
     * 序号
     */
    private Integer serialNumber;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 更新时间(自动维护)
     */
    private Date updateTime;

    /**
     * Get
     */
    public Long getCourseGameId() {
        return courseGameId;
    }

    /**
     * Set
     */
    public void setCourseGameId(Long courseGameId) {
        this.courseGameId = courseGameId;
    }
    /**
     * Get 游戏名称
     */
    public String getName() {
        return name;
    }

    /**
     * Set 游戏名称
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get 游戏信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set 游戏信息
     */
    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * Get 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    /**
     * Get 资源地址
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * Set 资源地址
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
    /**
     * Get 状态 1 ：生效 0 ：失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Set 状态 1 ：生效 0 ：失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * Get 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * Set 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * Get 序号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * Set 序号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long[] getGameIds() {
        return gameIds;
    }

    public void setGameIds(Long[] gameIds) {
        this.gameIds = gameIds;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
