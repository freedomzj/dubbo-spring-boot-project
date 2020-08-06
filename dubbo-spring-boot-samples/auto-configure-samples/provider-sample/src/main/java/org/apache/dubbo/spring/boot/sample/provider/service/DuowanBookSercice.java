package org.apache.dubbo.spring.boot.sample.provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.paperpass.book.consumer.BookService;

@DubboService(version = "1.0.0",group="duowan",cache="lru",timeout=3000)
public class DuowanBookSercice implements BookService {

	 @Value("${dubbo.application.name}")
    private String serviceName;
	
	@Override
	public String findByName(String name) {
		JsonObject jsonObject=new JsonObject();
		if(name!=null && !name.equals("")){
			jsonObject.addProperty("id",UUID.randomUUID().toString());
			jsonObject.addProperty("name","程序人生");
			jsonObject.addProperty("author","东野圭吾");
		}
		return jsonObject.toString();
	}

	@Override
	public List<String> findAll(String name) {
		JsonArray jsonArray=new JsonArray();
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("id",UUID.randomUUID().toString());
		jsonObject.addProperty("name","程序人生");
		jsonObject.addProperty("author","东野圭吾");
		jsonArray.add(jsonObject);
		
		jsonObject=new JsonObject();
		jsonObject.addProperty("id",UUID.randomUUID().toString());
		jsonObject.addProperty("name","程序人生1");
		jsonObject.addProperty("author","东野圭吾1");
		jsonArray.add(jsonObject);
		jsonObject.addProperty("id",UUID.randomUUID().toString());
		jsonObject.addProperty("name","程序人生2");
		jsonObject.addProperty("author","东野圭吾2");
		
		jsonObject=new JsonObject();
		jsonObject.addProperty("id",UUID.randomUUID().toString());
		jsonObject.addProperty("name","程序人生3");
		jsonObject.addProperty("author","东野圭吾3");
		jsonArray.add(jsonObject);
		List<String> strings=new ArrayList<>();
		strings.add(jsonArray.toString());
		return strings;
	}
	

	@Override
	public CompletableFuture<String> addBook(Map<String,Object> map) {
		 RpcContext savedContext = RpcContext.getContext();
	        // 建议为supplyAsync提供自定义线程池，避免使用JDK公用线程池
	        return CompletableFuture.supplyAsync(() -> {
	            System.out.println(savedContext.getAttachment("consumer-key1"));
	            try {
	            	saveBook(map);
	                Thread.sleep(5000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            return "async response from provider.";
	        });
	}
	
	
	String saveBook(Map<String,Object> map ) {
		 JsonObject jsonObject=new JsonObject();
			for (String str : map.keySet()) {
				jsonObject.addProperty(str, map.get(str).toString());;
			}
			return jsonObject.toString();
	 }

}
