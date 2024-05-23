package com.example.saveFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.saveFile.service.Service;

@SpringBootApplication
@RestController
@RequestMapping("/fileUpload")
public class SaveFilesApplication {
	
	@Autowired
	private Service service;
	
	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) throws IOException{
		String uploadFile = service.uploadInFolder(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws DataFormatException, IOException{
		byte[] files = service.downloadFile(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(files);
	}

	public static void main(String[] args) {
		SpringApplication.run(SaveFilesApplication.class, args);
	}

}
