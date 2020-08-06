package com.paperpass.book.consumer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface BookService {

	
	String findByName(String name);
	
	List<String> findAll(String name);
	
	CompletableFuture<String> addBook(Map<String, Object> map);
    
//    default CompletableFuture<String> addBook(Map<String, Object> map, String signal) {
//        return CompletableFuture.completedFuture(addBook(map));
//    }
}
