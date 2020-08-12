package com.example.demo.service;

import java.util.List;

import com.example.demo.model.fileUpload;

import org.springframework.web.multipart.MultipartFile;

public interface fileService {
    

    public int fileUpload(List<MultipartFile> multi,fileUpload file);
    public int fileDelete(fileUpload file);
    
}