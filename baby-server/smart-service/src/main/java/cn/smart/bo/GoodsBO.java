package cn.smart.bo;

/**
 * @author Ye
 */
public class GoodsBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 消耗积分
     */
    private Integer points;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 是否兑换 0 否 1 是
     */
    private Boolean exchanged;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getExchanged() {
        return exchanged;
    }

    public void setExchanged(Boolean exchanged) {
        this.exchanged = exchanged;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"points\":")
                .append(points);
        sb.append(",\"imageUrl\":\"")
                .append(imageUrl).append('\"');
        sb.append(",\"exchanged\":")
                .append(exchanged);
        sb.append('}');
        return sb.toString();
    }
}
