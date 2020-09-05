package com.order.system.bean;

import java.util.Date;

public class OrderBean {
	
	  private String customerName;
	  private String customerId;
	  private String address;
	  private Date orderDate;
	  private OrderItemBean orderItem;


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderItemBean getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItemBean orderItem) {
		this.orderItem = orderItem;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
