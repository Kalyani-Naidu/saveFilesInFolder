package com.example.saveFile.fileRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.saveFile.entity.FileData;

public interface FileRepo extends JpaRepository<FileData, Long>{
	
	Optional<FileData> findByName(String fileName);

}
