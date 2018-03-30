package com.stefan.order.pojo;

import java.io.Serializable;
import java.util.List;

import com.stefan.pojo.TbOrder;
import com.stefan.pojo.TbOrderItem;
import com.stefan.pojo.TbOrderShipping;

public class OrderInfo extends TbOrder implements Serializable {

	private static final long serialVersionUID = 2865151673408705921L;
	
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
