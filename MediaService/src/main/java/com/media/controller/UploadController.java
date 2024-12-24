package com.media.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.media.s3.S3Buckets;
import com.media.s3.S3Service;

import lombok.AllArgsConstructor;


/**
 * exampel api upload
 * **/
@RestController
@RequestMapping("/api/v1/upload")
@AllArgsConstructor
public class UploadController {
	
	private final S3Service s3Service;
	private final S3Buckets s3Buckets;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException{
		s3Service.putObject(s3Buckets.getMedia(),  "upload/"+ file.getOriginalFilename(), file.getBytes());
		return ResponseEntity.ok("success");
	}
	
	@GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> image(@RequestParam("imageName") String imageName){
		byte[] imageBytes = s3Service.getObject(s3Buckets.getMedia(), "upload/" + imageName);
		return ResponseEntity.ok(imageBytes);
	}

}
