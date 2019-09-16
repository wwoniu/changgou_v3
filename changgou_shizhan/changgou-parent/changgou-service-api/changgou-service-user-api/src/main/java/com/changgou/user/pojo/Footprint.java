package com.changgou.user.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
@Table(name="tb_footprint")
public class Footprint implements Serializable{

    @Column(name = "username")
	private String username;//

    @Column(name = "sku_image")
	private String skuImage;//

    @Column(name = "sku_name")
	private String skuName;//

    @Column(name = "sku_price")
	private Integer skuPrice;//

    @Column(name = "comment_num")
	private String commentNum;//

    @Column(name = "sku_id")
	private Long skuId;//

	@Id
    @Column(name = "id")
	private Long id;//



	//get方法
	public String getUsername() {
		return username;
	}

	//set方法
	public void setUsername(String username) {
		this.username = username;
	}
	//get方法
	public String getSkuImage() {
		return skuImage;
	}

	//set方法
	public void setSkuImage(String skuImage) {
		this.skuImage = skuImage;
	}
	//get方法
	public String getSkuName() {
		return skuName;
	}

	//set方法
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	//get方法
	public Integer getSkuPrice() {
		return skuPrice;
	}

	//set方法
	public void setSkuPrice(Integer skuPrice) {
		this.skuPrice = skuPrice;
	}
	//get方法
	public String getCommentNum() {
		return commentNum;
	}

	//set方法
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	//get方法
	public Long getSkuId() {
		return skuId;
	}

	//set方法
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}


}
