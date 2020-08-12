package com.example.demo.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.example.demo.DAO.fileDAO;
import com.example.demo.model.fileUpload;
import com.example.demo.service.fileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileServiceImpl implements fileService {

    @Autowired
    public fileDAO fileDAO;
    private static final String SAVE_PATH = "/upload";
    private static final String PREFIX_URL = "/upload/";
    String url = null;
    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public int fileUpload(List<MultipartFile> multi, fileUpload file) {
        int uploadCount = 0;
        try {
            // 파일 정보
            System.out.println(multi.size());
            for (int length = 0; length < multi.size(); length++) {
                String originFilename = multi.get(length).getOriginalFilename();// 필요
                file.setOrigin_name(originFilename);
                Long size = multi.get(length).getSize();
                file.setFile_size(size);
                // 서버에서 저장 할 파일 이름
                int k = originFilename.lastIndexOf(".");
                String saveFileName = originFilename.substring(0,k);
                file.setFile_name(saveFileName);
                writeFile(multi.get(length), originFilename);
                file.setFile_route(PREFIX_URL + originFilename);
                uploadCount += fileDAO.insertFile(file);
            }

        } catch (IOException e) {
            // 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
            // 편의상 RuntimeException을 던진다.
            // throw new FileUploadException();
            throw new RuntimeException(e);
        }

        return uploadCount;
    }

    private boolean writeFile(MultipartFile multi, String saveFileName) throws IOException {
        boolean result = false;
        byte[] data = multi.getBytes();
        FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
        fos.write(data);
        fos.close();
        return result;
    }

    @Override
    public int fileDelete(fileUpload file) {
        File fi = new File(fileDAO.selectFile(file));
        if (fi.exists()) {
            if (fi.delete())
                return fileDAO.deleteFile(file);
            else
                return 0;
        } else
            return 0;
    }

 

}