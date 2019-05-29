package com.wordanalyze.demo.POJO;

public class FileUpload {

    private String originalFileName;
    private String newFileName;

    public FileUpload(){}

    public void setOriginalFileName(String originalFileName){
        this.originalFileName = originalFileName;
    }

    public void setNewFileName(String newFileName){
        this.newFileName = newFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

}
