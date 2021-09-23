package cn.smart.bo;

public class ClockGroupGoodsBO {

    private Long goodsId;

    private String goodsName;

    private String goodsImageUrl;

    private Integer exchangePoints;

    // private String goodsCardImageUrl;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public Integer getExchangePoints() {
        return exchangePoints;
    }

    public void setExchangePoints(Integer exchangePoints) {
        this.exchangePoints = exchangePoints;
    }

//    public String getGoodsCardImageUrl() {
//        return goodsCardImageUrl;
//    }
//
//    public void setGoodsCardImageUrl(String goodsCardImageUrl) {
//        this.goodsCardImageUrl = goodsCardImageUrl;
//    }
}
