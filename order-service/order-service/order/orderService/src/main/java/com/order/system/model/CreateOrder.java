package com.order.system.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import net.sf.json.JSONObject;

public class CreateOrder {
	
	  private String customerName;
	  
	  private String customerId;
	  
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

}
