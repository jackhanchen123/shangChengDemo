package com.example.demo.pojo;

public class Buygoods {
//    private Integer buygoodsId;
	private Goods goods;
	
    private Integer buygoodsStuid;

    private Integer buygoodsBuynumber;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getBuygoodsStuid() {
		return buygoodsStuid;
	}

	public void setBuygoodsStuid(Integer buygoodsStuid) {
		this.buygoodsStuid = buygoodsStuid;
	}

	public Integer getBuygoodsBuynumber() {
		return buygoodsBuynumber;
	}

	public void setBuygoodsBuynumber(Integer buygoodsBuynumber) {
		this.buygoodsBuynumber = buygoodsBuynumber;
	}   
    
}