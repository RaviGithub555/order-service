package com.order.system.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="orders")
public class Order {
	
	  @Id
	  private String mongoId;
	  
	  @Field("ORDER_ID")
	  private Long orderId;
	  
	  @Field("PRODUCT")
	  private String product;
	  
	  @Field("CUSTOMER_NAME")
	  private String customerName;
	  
	  @Field("SHIPPING_ADDRESS")
	  private String shippingAddress;
	  
	  @Field("CUSTOMER_ID")
	  private String customerId;
	  
	  @Field("ORDER_DATE")
	  private String orderDate;
	  
	  @Field("ORDER_ITEM")
	  private OrderItem orderDetails;
	  
	 

	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public OrderItem getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderItem orderItem) {
		this.orderDetails = orderItem;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}