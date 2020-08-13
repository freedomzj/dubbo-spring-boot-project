package org.apache.dubbo.spring.boot.sample.provider.consumer.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paperpass.book.consumer.BookService;
import com.paperpass.book.consumer.IFileService;

@RestController
public class BookController {

	
	@DubboReference(version = "${book.service.version}", url = "${book.service.url}",group="duowan")
    private BookService bookService;
	 
	@DubboReference(version = "${file.service.version}", url = "${file.service.url}",group="duowan-file")
    private  IFileService IFileService;
	
    @RequestMapping("bookList")
	public List<String>  book(String name){
		return bookService.findAll(name);
	}
    
    
    @RequestMapping("bookUpload")
    @ResponseBody
   	public Map<String, Object>  bookUpload(String base64Str){
    	Map<String, Object>  map=IFileService.base64StrUpload(base64Str);
    	return map;
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
