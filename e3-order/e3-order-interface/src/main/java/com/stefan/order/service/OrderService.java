package com.stefan.order.service;

import com.stefan.common.utils.E3Result;
import com.stefan.order.pojo.OrderInfo;

public interface OrderService {
	
	E3Result createOrder(OrderInfo orderInfo);
	
}
