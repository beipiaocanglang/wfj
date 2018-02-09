package com.wdzsj.cmn.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public class FileUploadUtil {
	
	private static final int BUFFER_SIZE = 100 * 1024;
	
    public static String upload(MultipartFile file, String uploadPath,HttpServletRequest request) {
		InputStream in = null;
	    OutputStream out = null;
	    String reaPath =  null;
	    try {
	    	
	    	String fileName=file.getOriginalFilename();           
	    	String type = fileName.substring(fileName.lastIndexOf("."));
	    	int a = 1000;
			int b = 9999;
			String filename = String.valueOf(System.currentTimeMillis()) + String.valueOf((long)Math.rint(Math.random()*(b-a+1)+a));
	    	
            reaPath = "assets" + "/" + uploadPath + "/" + filename + type;
            String dstPath = request.getRealPath("") + File.separator + "assets" + File.separator + uploadPath + File.separator + filename + type;
			String dstPathDir = request.getRealPath("") + File.separator + "assets" + File.separator + uploadPath;
	    	//判断路径是否存在
            File dstDir = new File(dstPathDir);
	    	if(!dstDir.exists()){
	    		dstDir.mkdirs();
	    	}
	    	
	    	File dstFile = new File(dstPath);  
			in = file.getInputStream();
			out = new BufferedOutputStream(new FileOutputStream(dstFile),BUFFER_SIZE);
			fileUpload(in, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(null != in) {
					in.close();
					in = null;
				}
				if(null != out) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reaPath;
	}
		
	private static void fileUpload(InputStream in, OutputStream out) {
        try {
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int len = 0;
			while ((len = in.read(buffer)) > 0) {
			    out.write(buffer, 0, len);
			    out.flush();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != in) {
					in.close();
					in = null;
				}
				if(null != out) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
	
}
