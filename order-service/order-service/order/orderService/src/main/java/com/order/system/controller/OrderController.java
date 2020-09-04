package com.order.system.controller;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.order.system.model.CreateOrder;
import com.order.system.model.Order;
import com.order.system.model.OrderItem;
import com.order.system.model.OrderItemBean;
import com.order.system.repository.OrderRepository;
import com.order.system.service.OrderService;
import com.order.system.util.DateUtil;
import com.order.system.web.exception.BadRequestException;
import com.order.system.web.exception.ResourceNotFoundException;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private static final Logger log = LogManager.getLogger(OrderController.class);

    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    OrderService orderService;

    @GetMapping
    public Page<Order> findAll(@RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            page = 1;
        }
        log.info("Find orders on page {}", page);
        PageRequest pageRequest = new PageRequest(page - 1, 10, Sort.Direction.DESC, "orderDate");
        return orderRepository.getOrdersByPage(pageRequest);
    }

    @GetMapping("/existingOrder/{id}")
    public Order findOne(@PathVariable Long id) {
        log.info("Find order with id {}", id);
        return (Order) Optional.ofNullable(orderRepository.findOne(id))
                .orElseThrow(ResourceNotFoundException::new);
    }
    
    @PostMapping("/createOrder")
	public ResponseEntity<JSONObject> creatOrder(@RequestBody CreateOrder createOrd)throws Exception
	{
		JSONObject jsonRes = null;
		try {
			if(createOrd!=null && !createOrd.equals(""))
			{
				 Random randm = null;
					try {
						randm = SecureRandom.getInstanceStrong();
					} catch (NoSuchAlgorithmException e) {
						 throw new BadRequestException();
					}
			        int orderId = randm.nextInt(9000000) + 1000000;
				
				Order order = new Order();
				order.setOrderId(Long.valueOf(orderId));
				order.setOrderDate(DateUtil.getNowCurrentDateTime());
				OrderItem orderItem = new OrderItem();
				OrderItemBean ordBean = createOrd.getOrderItem();
				orderItem.setProductCode(ordBean.getProductCode());
				orderItem.setProductName(ordBean.getProductName());
				orderItem.setQuantity(ordBean.getQuantity());
				orderItem.setProductPrice(ordBean.getProductPrice());
				order.setOrderDetails(orderItem);
				Order orderCreated =orderService.createOrder(order);
				if(orderCreated!=null && !orderCreated.equals(""))
				{
					jsonRes = new JSONObject();
					jsonRes.put("order", "SUCCESS");
					jsonRes.put("orderStatus", "SUCCESSFULLY CREATED");
				}else {
					jsonRes = new JSONObject();
					jsonRes.put("order", "FAILED");
					jsonRes.put("orderStatus", "NOT CREATED");
				}
			}
		} catch (Exception e) {
			jsonRes = new JSONObject();
			jsonRes.put("order", "NOT SUCCESS");
			jsonRes.put("orderStatus", "NOT CREATED");
			return new ResponseEntity<JSONObject>(jsonRes, HttpStatus.OK);
		}
		return new ResponseEntity<JSONObject>(jsonRes, HttpStatus.OK);
	}
}
