package com.duowan.store;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.paperpass.book.consumer.BookService;

@EnableAutoConfiguration
public class BookStoreApp {
	 private final Logger logger = LoggerFactory.getLogger(getClass());

	 	@DubboReference(version = "${book.service.version}",group="${book.service.group}")
	    private BookService bookService;

	    public static void main(String[] args) {
	        SpringApplication.run(BookStoreApp.class);
	    }

	    @Bean
	    public ApplicationRunner runner() {
	        return args -> logger.info(bookService.findAll("mercyblitz").toString());
	    }
}
