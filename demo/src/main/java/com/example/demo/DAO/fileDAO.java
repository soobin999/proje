package com.example.demo.DAO;

import java.util.List;

import com.example.demo.model.fileUpload;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface fileDAO {
    
    int insertFile(fileUpload file);
    String selectFile(fileUpload file);
    List<fileUpload> selectAllFile();
    int deleteFile(fileUpload file);
    String selectFileName(fileUpload file);
}