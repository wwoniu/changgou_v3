package com.changgou.pingjia.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
@Table(name="tb_order_evaluation")
public class OrderEvaluation implements Serializable{

	@Id
    @Column(name = "id")
	private Long id;//

    @Column(name = "username")
	private String username;//

    @Column(name = "order_id")
	private Integer orderId;//

    @Column(name = "scorce")
	private Integer scorce;//

    @Column(name = "details")
	private String details;//

    @Column(name = "user_image")
	private String userImage;//

    @Column(name = "sku_id")
	private Long skuId;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getUsername() {
		return username;
	}

	//set方法
	public void setUsername(String username) {
		this.username = username;
	}
	//get方法
	public Integer getOrderId() {
		return orderId;
	}

	//set方法
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	//get方法
	public Integer getScorce() {
		return scorce;
	}

	//set方法
	public void setScorce(Integer scorce) {
		this.scorce = scorce;
	}
	//get方法
	public String getDetails() {
		return details;
	}

	//set方法
	public void setDetails(String details) {
		this.details = details;
	}
	//get方法
	public String getUserImage() {
		return userImage;
	}

	//set方法
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	//get方法
	public Long getSkuId() {
		return skuId;
	}

	//set方法
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}


}
