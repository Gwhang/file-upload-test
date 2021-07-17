package com.example.fileuploadtest.entity;

import javax.persistence.*;

@Entity
@Table(name = "fileInfo")
public class FileInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column(name = "fileName",length = 255)
    private String fileName;

    @Column(name = "filePath",length = 255)
    private String filePath;

    @Column(name = "originFileName",length = 50)
    private String originFileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public FileInfo() {
    }

    public FileInfo(Integer id, String fileName, String filePath, String originFileName) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.originFileName = originFileName;
    }
}
