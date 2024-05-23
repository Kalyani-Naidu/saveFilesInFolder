package com.example.saveFile.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.saveFile.entity.FileData;
import com.example.saveFile.fileRepository.FileRepo;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private FileRepo repo;
	
	private final String FOLDER_PATH = "C:\\Users\\Affan\\Desktop\\MyFiles\\";
	
	public String uploadInFolder(MultipartFile file) throws IOException {
			String filePath = FOLDER_PATH+file.getOriginalFilename();
			FileData fileData = repo.save(FileData.builder()
					.name(file.getOriginalFilename())
					.type(file.getContentType())
					.filePath(filePath)
					.build()
					);
			
			file.transferTo(new File(filePath));
			if(fileData != null ) {
				return "file uploaded successfully : "+file.getOriginalFilename();
			}
			return null;
}
	
	public byte[] downloadFile(String fileName) throws IOException {
		Optional<FileData> fileData = repo.findByName(fileName);
		String filePath = fileData.get().getFilePath();
		byte[] files = Files.readAllBytes(new File(filePath).toPath());
		return files;
	}
	
}	
