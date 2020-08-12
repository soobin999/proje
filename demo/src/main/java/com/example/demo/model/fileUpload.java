package com.example.demo.model;

import java.sql.Date;

public class fileUpload {
    
    private String file_name;
    private String file_route;
    private Long file_size;
    private Date upload_date;
    private String user_no;
    private String origin_name;

    public String getFile_name() {
        return this.file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_route() {
        return this.file_route;
    }

    public void setFile_route(String file_route) {
        this.file_route = file_route;
    }

    public Long getFile_size() {
        return this.file_size;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public Date getUpload_date() {
        return this.upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }

    public String getUser_no() {
        return this.user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getOrigin_name() {
        return this.origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

}