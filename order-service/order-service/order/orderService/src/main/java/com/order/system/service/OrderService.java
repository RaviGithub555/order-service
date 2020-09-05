package com.order.system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.order.system.bean.OrderBean;
import com.order.system.documents.Order;


@Service
public interface OrderService {
	
	public Order submitOrder(OrderBean orderBean);

	public Order findOne(Long id);

	public Page<Order> getOrdersByPage(PageRequest pageRequest);

}
