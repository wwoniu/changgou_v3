package com.changgou.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:Record构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_record")
public class Record implements Serializable{

	@Id
    @Column(name = "id")
	private String id;//

    @Column(name = "order_id")
	private String orderId;//

    @Column(name = "username")
	private String username;//

    @Column(name = "is_overtime")
	private String isOvertime;//

    @Column(name = "is_remind")
	private String isRemind;//

    @Column(name = "is_delivery")
	private String isDelivery;//



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getOrderId() {
		return orderId;
	}

	//set方法
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getIsOvertime() {
		return isOvertime;
	}

	//set方法
	public void setIsOvertime(String isOvertime) {
		this.isOvertime = isOvertime;
	}
	//get方法
	public String getIsRemind() {
		return isRemind;
	}

	//set方法
	public void setIsRemind(String isRemind) {
		this.isRemind = isRemind;
	}
	//get方法
	public String getIsDelivery() {
		return isDelivery;
	}

	//set方法
	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}


}
