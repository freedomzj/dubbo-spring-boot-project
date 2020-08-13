package com.bookstore.provider.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.paperpass.book.consumer.IFileService;

@DubboService(version = "1.0.0",group="duowan-file",timeout=3000)
public class FileService implements IFileService {

	@Value("${upload.path}") 
	String uploadPath;
	
	@Override
	public Map<String, Object> base64StrUpload(String base64Str) {
		System.out.println(base64Str);
		Map<String, Object> result=new HashMap<String, Object>();
		String msg="";
		Integer code=0;
		  if (StringUtils.isEmpty(base64Str)) {
			  msg="base64字符串为空";
			  code=-1;
	        }
	        base64Str = base64Str.replaceAll("data:image/jpeg;base64,", "");
	        
	        try {
	        	//;
	            // Base64解码
	            byte[] imgByte = Base64.getDecoder().decode(base64Str);
	            // 生成jpeg图片
	            String fileName = new String((uploadPath+UUID.randomUUID().toString().replace("-", "") + ".jpg").getBytes("gb2312"), "ISO8859-1");
	            // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
//	            GridFSFile gridFSFile = gridFsTemplate.store(is, fileName, "image/jpeg");
//	            FileInfoAO fileInfo = new FileInfoAO();
//	            fileInfo.setFileName(fileName);
//	            fileInfo.setContentType("image/jpeg");
//	            fileInfo.setMongoFileId(gridFSFile.getId().toString());
	            writeFileByBytes(fileName,imgByte,false);
	            msg="success";
	            code=1;
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	e.getMessage();
	           msg="图片上传失败";
	           code=-1;
	        }
	        
	        result.put("msg", msg);
	        result.put("code", code);
	        return result;
	}
	
	
	/**
	 * 向文件写入byte[]
	 * 
	 * @param fileName 文件名
	 * @param bytes    字节内容
	 * @param append   是否追加
	 * @throws IOException
	 */
	public static void writeFileByBytes(String fileName, byte[] bytes, boolean append) throws IOException {
		try(OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, append))){
			out.write(bytes);
		}
	}
	
	public static void main(String[] args) throws IOException {
		byte[] data=readFileByBytes("/work/upload/test2.jpg");
		 String encoded = Base64.getEncoder().encodeToString(data);
		System.out.println(encoded);
		byte[] str=Base64.getDecoder().decode(encoded);
		 writeFileByBytes("/work/upload/a.jpg",str,false);
		
	}
	
	public static byte[] readFileByBytes(String fileName) throws IOException {
		try (InputStream in = new BufferedInputStream(new FileInputStream(fileName)); 
			 ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			byte[] tempbytes = new byte[in.available()];
			for (int i = 0; (i = in.read(tempbytes)) != -1;) {
				out.write(tempbytes, 0, i);
			}
			return out.toByteArray();
		}
	}



}
