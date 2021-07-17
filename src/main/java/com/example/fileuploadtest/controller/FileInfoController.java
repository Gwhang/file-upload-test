package com.example.fileuploadtest.controller;

import com.example.fileuploadtest.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/File")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;


    /**
     * 下载模板
     * @param response
     * @throws Exception
     */
    @GetMapping("/downloadTemp")
    public void downloadTemp(HttpServletResponse response)throws Exception{
        this.fileInfoService.downloadTemp(response);
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        this.fileInfoService.upload(file,request);
        return "成功";
    }

    /**
     * 下载文件
     * @param response
     * @param id
     */
    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response,Integer id) throws Exception {
        this.fileInfoService.downloadOriginFile(response,id);
    }


}
