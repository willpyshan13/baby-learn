package cn.smart.bo;

import java.util.Set;

public class PersonPushSettingBO {

    /**
     * 用户id
     */
    private Long personId;

    /**
     * 推送别名
     */
    private String pushAlias;

    /**
     * 非持久化属性
     * 推送标签(分组)
     */
    private Set<String> pushTags;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPushAlias() {
        return pushAlias;
    }

    public void setPushAlias(String pushAlias) {
        this.pushAlias = pushAlias;
    }

    public Set<String> getPushTags() {
        return pushTags;
    }

    public void setPushTags(Set<String> setPushTag) {
        this.pushTags = setPushTag;
    }

    @Override
    public String toString() {
        return "PersonPushSettingBO{" +
                "personId=" + personId +
                ", pushAlias='" + pushAlias + '\'' +
                ", pushTags=" + pushTags +
                '}';
    }
}
