package com.order.system.controller;

import java.util.Optional;
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
import com.order.system.bean.OrderBean;
import com.order.system.documents.Order;
import com.order.system.service.OrderService;
import com.order.system.exception.ResourceNotFoundException;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api/orderService")
public class OrderController {
	
	private static final Logger log = LogManager.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public Page<Order> findAllOrders(@RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            page = 1;
        }
        log.info("Find orders on page {}", page);
        PageRequest pageRequest = new PageRequest(page - 1, 10, Sort.Direction.DESC, "orderDate");
        return orderService.getOrdersByPage(pageRequest);
    }

    @GetMapping("/{id}")
    public Order findOne(@PathVariable Long id) {
        log.info("Find order with id {}", id);
        return (Order) Optional.ofNullable(orderService.findOne(id))		
                .orElseThrow(ResourceNotFoundException::new);
    }
    
    @PostMapping("/placeOrder")
	public ResponseEntity<JSONObject> placeOrder(@RequestBody OrderBean orderBean)throws Exception
	{
		JSONObject jsonRes = null;
		try {
			if(orderBean!=null && !orderBean.equals(""))
			{
				Order newOrder =orderService.submitOrder(orderBean);
				if(newOrder!=null && !newOrder.equals(""))
				{
					jsonRes = new JSONObject();
					jsonRes.put("order", "SUCCESS");
					jsonRes.put("orderStatus", "YOUR ORDER PLACED WITH ORDERID :" + newOrder.getOrderId());
				}else {
					jsonRes = new JSONObject();
					jsonRes.put("order", "FAILED");
					jsonRes.put("orderStatus", "YOUR OREDER NOT PLACED");
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
