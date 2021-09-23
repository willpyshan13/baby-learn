package cn.smart.support.jpush;

public class PushMessage {

    public interface PushType {
        int CLASS_BEGIN = 1;
        int LOGOUT = 2;
        int UPDATE_TAG = 3;
    }

    /**
     * 推送类型 1：直播消息 2：强制退出通知 3:更新班级推送tag标签消息
     */
    private Integer type;

    /**
     * 推送消息
     */
    private String msg;

    /**
     * 推送标题
     */
    private String title;

    /**
     * 消息图片
     */
    private String imageUrl;

    /**
     * 消息音频
     */
    private String audioUrl;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":")
                .append(type);
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"imageUrl\":\"")
                .append(imageUrl).append('\"');
        sb.append(",\"audioUrl\":\"")
                .append(audioUrl).append('\"');
        sb.append('}');
        return sb.toString();
    }

}
