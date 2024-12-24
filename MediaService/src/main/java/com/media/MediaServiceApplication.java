package com.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.media.s3.S3Buckets;
import com.media.s3.S3Service;

@SpringBootApplication
public class MediaServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MediaServiceApplication.class, args);
	}
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private S3Buckets s3Buckets;

	@Override
	public void run(String... args) throws Exception {
//		s3Service.putObject(s3Buckets.getCustomer(), "upload/file", "Hello".getBytes());
//		
//		byte[] s3Bytes = s3Service.getObject(s3Buckets.getCustomer(), "upload/file");
//		
//		System.out.println("result " + new String(s3Bytes));
		
	}

}
