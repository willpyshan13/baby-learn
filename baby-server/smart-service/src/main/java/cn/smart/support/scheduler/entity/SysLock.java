package cn.smart.support.scheduler.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

public class SysLock extends BaseEntity {

    private String key;

    private String name;

    private Integer expireSecond;

    private Date createTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExpireSecond() {
        return expireSecond;
    }

    public void setExpireSecond(Integer expireSecond) {
        this.expireSecond = expireSecond;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"key\":\"")
                .append(key).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"expireSecond\":")
                .append(expireSecond);
        sb.append('}');
        return sb.toString();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
