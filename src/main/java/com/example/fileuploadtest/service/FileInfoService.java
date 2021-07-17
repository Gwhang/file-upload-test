package com.example.fileuploadtest.service;

import com.example.fileuploadtest.dao.FileInfoRepository;
import com.example.fileuploadtest.entity.FileInfo;
import com.example.fileuploadtest.util.DownloadUtil;
import com.example.fileuploadtest.util.ResponseUtil;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class FileInfoService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    private String filePath="/export/file/";

    /**
     * 下载模板
     * @param response
     * @throws Exception
     */
    public void downloadTemp(HttpServletResponse response) throws Exception {
        DownloadUtil.localDownload("template.csv","测试模板.csv",response);
    }

    /**
     * 上传文件
     * @param file
     * @param request
     * @throws IOException
     */
    public void upload(MultipartFile file, HttpServletRequest request) throws IOException {
        // 上传文件到云磁盘
        String objectName = "test_" + UUID.randomUUID().toString();
        // 拼接路径
        String path=filePath + objectName;
        File resultFile = new File(path);
        // 创建临时文件
        File tempFile = File.createTempFile("uploadTemp" + UUID.randomUUID().toString(), "temp");
        file.transferTo(tempFile);
        // 拷贝文件
        FileUtils.copyFile(tempFile, resultFile);
        // 删除临时文件
        Files.delete(Paths.get(tempFile.getPath()));
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginFileName(file.getOriginalFilename());
        fileInfo.setFilePath(path);
        fileInfo.setFileName(objectName);

        this.fileInfoRepository.save(fileInfo);
    }

    /**
     * 下载文件
     * @param response
     * @param id
     */
    public void downloadOriginFile(HttpServletResponse response, Integer id) throws Exception {
        // 查询原始数据信息
        Optional<FileInfo> fileInfo = fileInfoRepository.findById(id);
        if(fileInfo.isPresent()){
            FileInfo info = fileInfo.get();
            ResponseUtil.asFile(response, info.getOriginFileName());
            File file = new File(info.getFilePath());
            InputStream in = new FileInputStream(file);
            this.download(in, response, info.getOriginFileName());

        }
    }
    private void download(InputStream in, HttpServletResponse response, String fileName) throws IOException {
        try {
            ResponseUtil.asFile(response, fileName);
            ByteStreams.copy(in, response.getOutputStream());
        } catch (IOException e) {
            log.error(" IOException 文件下载失败>>>", e);
        } catch (Exception e) {
            log.error(" Exception 文件下载失败>>>", e);
        } finally {
            in.close();
        }
    }



}
