package com.example.fileuploadtest.util;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class ResponseUtil {
    /**
     * 设置指定文件名及其样式
     * @param response
     * @param fileName 文件全名
     * @throws Exception
     */
    public static void asFile(HttpServletResponse response, String fileName) throws Exception {
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        int index = fileName.lastIndexOf('.');
        if (index < 0) {
            throw new Exception("文件名不包含.");
        }
        String suffix = fileName.substring(index + 1);
        if ("csv".equalsIgnoreCase(suffix)) {
            response.setContentType("application/csv;charset=UTF-8");
        } else if("xlsx".equalsIgnoreCase(suffix)) {
            response.setContentType("application/msexcel;charset=UTF-8");
        } else if ("zip".equalsIgnoreCase(suffix)) {
            response.setContentType("application/x-zip-compressed;charset=UTF-8");
        }
        else {
            throw new Exception("暂不支持设置成" + suffix + "文件格式");
        }
    }
}