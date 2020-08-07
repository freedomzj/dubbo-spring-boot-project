package com.paperpass.book.consumer;

import java.util.Map;

public interface IFileService {
	
	Map<String, Object> base64StrUpload(String base64Str);

}
