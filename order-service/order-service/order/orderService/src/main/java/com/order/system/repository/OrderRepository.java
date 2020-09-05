package com.order.system.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.order.system.documents.Order;
import com.order.system.documents.OrderItem;


@Repository
public class OrderRepository{
	
@Autowired
MongoOperations mongoOperations;
   

public Page<Order> getOrdersByPage(Pageable pageable) {
	List<Order> orderList = null;
	Page<Order> orderPage = null;
	try {
		Query query = new Query().with(pageable);
		orderList = mongoOperations.find(query, Order.class, "orders");
		
		orderPage = PageableExecutionUtils.getPage(
				orderList,
		        pageable,
		        () -> mongoOperations.count(query, Order.class));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return orderPage;
}

	public Order saveOrder(Order order) {
		Order ordr = null;
		try {
			ordr =mongoOperations.save(order, "orders");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordr;
		
	}

	public OrderItem saveOrderItem(OrderItem orderItem) {
		
		OrderItem ordr = null;
		try {
			ordr =mongoOperations.save(orderItem, "order_Item");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordr;
	}

	public Order findOne(Long id) {
		Order order = null;
		try {
			Query query = new Query(Criteria.where("ORDER_ID").is(id));
			order = mongoOperations.findOne(query, Order.class, "orders");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
}
