package com.example.demo.pojo;

public class Goods {
    private Integer goodsId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsNumber;

    private Integer goodsTypeid;

    private String goodsImg;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsTypeid() {
        return goodsTypeid;
    }

    public void setGoodsTypeid(Integer goodsTypeid) {
        this.goodsTypeid = goodsTypeid;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsNumber=" + goodsNumber + ", goodsTypeid=" + goodsTypeid + ", goodsImg=" + goodsImg + "]";
	}
    
}