package org.apache.dubbo.spring.boot.sample.consumer.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paperpass.book.consumer.BookService;

@RestController
public class BookController {

	
//	 @DubboReference(
//	            version = "1.0.0",
//	            url = "dubbo ://127.0.0.1:12345",
//	            timeout = 3000,
//        		group="duowan",
//	            methods = {
//	                    @Method(name = "findByName", timeout = 300),
//	                    @Method(name = "findAll", timeout = 300)
//	            }
//	    )
	 @DubboReference(version = "${demo.service.version}", url = "${demo.service.url}")
    private BookService bookService;
	
    @RequestMapping("bookList")
	public List<String>  book(String name){
		bookService.findAll(name);
		return bookService.findAll(name);
	}
    

    @RequestMapping("bookAdd")
	public String  bookAdd(Model model){
    	Map<String, Object> map=model.asMap();
//    	 RpcContext.getContext().asyncCall(
//    		    () -> {
//    		    	bookService.addBook(map);
//    		    }
//    		);
    	// 此调用会立即返回null
    	 bookService.addBook(map);
    	 // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future
    	 CompletableFuture<String> helloFuture = RpcContext.getContext().getCompletableFuture();
    	 // 为Future添加回调
    	 helloFuture.whenComplete((retValue, exception) -> {
    	     if (exception == null) {
    	         System.out.println(retValue);
    	     } else {
    	         exception.printStackTrace();
    	     }
    	 });

    	return "save Success";
    	
    	
//		return future.get();
	}
	
}
