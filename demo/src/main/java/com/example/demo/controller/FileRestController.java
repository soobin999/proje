package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import com.example.demo.DAO.fileDAO;
import com.example.demo.model.fileUpload;
import com.example.demo.service.fileService;
import org.springframework.core.io.ResourceLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileRestController {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired(required = false)
    fileService fileService;
    @Autowired(required = false)
    fileDAO fileDAO;

    @RequestMapping("/fileUpload")
    public Object fileUpLoad(List<MultipartFile> multi, fileUpload file) {
        System.out.println(multi.get(0));
        System.out.println(multi.get(1));
        HashMap<String, Integer> upload = new HashMap<>();
        file.setUser_no("ss");
        upload.put("upload", fileService.fileUpload(multi, file));
        return upload;
    }

    @RequestMapping("/delete")
    public Object fileDelete(fileUpload file) {
        HashMap<String, Integer> delete = new HashMap<>();
        delete.put("delete", fileService.fileDelete(file));
        // 추가적인 보충필요
        return delete;
    }

    @RequestMapping("/view")
    public Object fileselect() {
        List<fileUpload> selefile = fileDAO.selectAllFile();
        return selefile;
    }

    @RequestMapping("/down")
    public ResponseEntity<Resource> fileDownload(fileUpload file) throws IOException {

        Resource resource = resourceLoader.getResource("file:C:/upload/" + fileDAO.selectFileName(file));
        System.out.println(resource);
        File fi = resource.getFile();
        System.out.println(fi);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, fileDAO.selectFileName(file)) // 다운 받아지는 파일 명 설정
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fi.length())) // 파일 사이즈 설정
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString()) // 바이너리 데이터로 받아오기 설정
                .body(resource);

    }

    @PostMapping("/getfile")
    public void getLegacyDrawFile(@RequestBody JSONObject request, HttpServletResponse response) throws Exception {
        String filePath = "C:\\upload" + File.separator + request.get("filename");
        OutputStream outs = null;
        FileInputStream fis = null;
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + request.get("filename") + "\";");
            outs = response.getOutputStream();
            try {
                fis = new FileInputStream(filePath);
                int ch;
                while ((ch = fis.read()) != -1) {
                    outs.write(ch);
                }
                outs.close();
                fis.close();
                outs.flush();
            } catch (IOException e) {
                response.setContentLength(0);
                System.out.println("파일을 찾지 못했습니다");
                e.printStackTrace();
            } finally {
                if (outs != null) {
                    outs.close();
                    outs = null;
                }
                if (fis != null) {
                    fis.close();
                    fis = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
