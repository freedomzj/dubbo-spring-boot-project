package com.duowan.store.service;

import java.util.UUID;

import org.apache.dubbo.config.annotation.DubboService;

import com.paperpass.book.consumer.IOrderService;

@DubboService(version = "1.0.0",group="duowan-order",timeout=3000)
public class OrderService  implements IOrderService{

	@Override
	public String findByOrderId(String orderId) {
		String ret="";
		if(orderId!=null && orderId.length()>0){
			 ret=UUID.randomUUID().toString();
		}
		return ret;
	}

}
