package com.duowan.store;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.duowan.store.service.OrderService;
import com.paperpass.book.consumer.BookService;

@EnableAutoConfiguration
@DubboComponentScan(basePackages="com.duowan.store.service")
public class BookStoreApp {
	 private final Logger logger = LoggerFactory.getLogger(getClass());

	 	@DubboReference(version = "${book.service.version}",group="${book.service.group}")
	    private BookService bookService;
	 	
//		@DubboReference(version = "${order.service.version}",group="${order.service.group}")
	 	@Autowired
	    private OrderService orderService;

	    public static void main(String[] args) {
	        SpringApplication.run(BookStoreApp.class);
	    }

	    @Bean
	    public ApplicationRunner runner() {
	        return args -> logger.info(bookService.findAll("mercyblitz").toString()+orderService.findByOrderId("123456"));
	    }
}
