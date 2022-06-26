package com.example.demo.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;

@RestController
@RequestMapping("/common")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class UploadController {
	@RequestMapping(path = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String uploadFile(MultipartFile file,Integer price) throws IOException, ServletException {
		System.out.println(price);
		if (file.isEmpty() || file.getSize() == 0) {
			return "文件为空";
		}
		try {
			MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin"); // 连接
			if (!minioClient.bucketExists("goods")) { // 是否存在名为“test”的bucket
				minioClient.makeBucket("goods");
			}
			String fileName = file.getOriginalFilename();
			String newName = UUID.randomUUID().toString().replaceAll("-", "")+fileName.substring(fileName.lastIndexOf("."));
			InputStream inputStream = file.getInputStream(); // 获取file的inputStream
			//image/png
			minioClient.putObject("goods", newName, inputStream,"image/png"); // 上传
			//minioClient.putObject("goods", newName, inputStream, "application/octet-stream"); // 上传
			inputStream.close();
			String url = minioClient.getObjectUrl("goods", newName); // 文件访问路径
			System.out.println(url);
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "上传失败";
	}
}
