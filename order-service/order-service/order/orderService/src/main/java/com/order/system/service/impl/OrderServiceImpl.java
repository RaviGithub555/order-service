package com.order.system.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.order.system.bean.OrderBean;
import com.order.system.bean.OrderItemBean;
import com.order.system.documents.Order;
import com.order.system.documents.OrderItem;
import com.order.system.repository.OrderRepository;
import com.order.system.service.OrderService;
import com.order.system.util.DateUtil;
import com.order.system.exception.BadRequestException;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

	@Override
	public Order submitOrder(OrderBean orderRequest) {
		 log.info("Create new {}", orderRequest);	
		 if (orderRequest== null) {
		        throw new BadRequestException();
		    }
		 
		 Random randm = null;
			try {
				randm = SecureRandom.getInstanceStrong();
			} catch (NoSuchAlgorithmException e) {
				 throw new BadRequestException();
			}
	        int orderId = randm.nextInt(9000000) + 1000000;
		
	    Order submitOrder = new Order();
		submitOrder.setOrderId(Long.valueOf(orderId));
		submitOrder.setOrderDate(DateUtil.getNowCurrentDateTime());
		submitOrder.setCustomerId(orderRequest.getCustomerId());
		submitOrder.setCustomerName(orderRequest.getCustomerName());
		submitOrder.setShippingAddress(orderRequest.getAddress());
		
		OrderItem orderItem = new OrderItem();
		OrderItemBean ordBean = orderRequest.getOrderItem();
		orderItem.setProductCode(ordBean.getProductCode());
		orderItem.setProductName(ordBean.getProductName());
		orderItem.setQuantity(ordBean.getQuantity());
		orderItem.setProductPrice(ordBean.getProductPrice());
		submitOrder.setOrderDetails(orderItem);
		 
		 Order order = orderRepository.saveOrder(submitOrder);
		return order;
	}

	@Override
	public Order findOne(Long id) {
		Order order = null;
		try {
			order = orderRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public Page<Order> getOrdersByPage(PageRequest pageRequest) {
		Page<Order> orderList = null;
		try {
			orderList = orderRepository.getOrdersByPage(pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

}

