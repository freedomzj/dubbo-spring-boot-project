package org.apache.dubbo.spring.boot.sample.consumer.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paperpass.book.consumer.BookService;

@RestController
@RequestMapping("/user")
public class UserController {
    //将服务注册到不同的注册中心，从不同的注册中心取
//	@DubboReference(version="1.0.0",registry = "registry1")
	 private BookService bookService;
    
//	@DubboReference(version="1.0.0",registry="registry2")
	 private BookService bookService2;
 
    @GetMapping("/hello1/{name}")
    public List<String>  sayHello(@PathVariable("name")String name)throws Exception{
        System.out.println("调用了第一个地址");
        return bookService.findAll(name);
    }
    @GetMapping("/hello2/{name}")
    public List<String>  sayHello2(@PathVariable("name")String name)throws Exception{
        System.out.println("调用了第二个地址");
        return bookService2.findAll(name);
    }
}
 
