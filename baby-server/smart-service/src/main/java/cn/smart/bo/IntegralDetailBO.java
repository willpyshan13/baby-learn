package cn.smart.bo;

/**
 * app 积分详情
 * @author Ye
 */
public class IntegralDetailBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 积分记录来源
     * 1 : 学习获得
     * 2 : 复习获得
     * 3 : 打卡获得
     * 4 : 观看视频获得
     * 5 : 兑换物品消耗
     * 6 : 兑换 Lipa 游戏消耗
     */
    private Integer source;

    /**
     * 获得时间/兑换时间
     */
    private String createDate;

    /**
     * 积分名称
     */
    private String name;

    /**
     * 物品名称
     */
    private String goodsName;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 物品图片
     */
    private String goodsUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"number\":")
                .append(number);
        sb.append(",\"source\":")
                .append(source);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"goodsName\":\"")
                .append(goodsName).append('\"');
        sb.append(",\"goodsUrl\":\"")
                .append(goodsUrl).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
