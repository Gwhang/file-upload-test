package com.example.fileuploadtest.util;

import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/***
 * 下载文件，。
 */
public class DownloadUtil {

    /***
     *
     */
    private DownloadUtil() {

    }

    /**
     * @param filePath       文件路径，相对于本地resources的路径
     * @param outputFileName 输出文件名
     * @param response       HttpServletResponse类型
     * @throws Exception
     */
    public static void localDownload(String filePath, String outputFileName, HttpServletResponse response) throws Exception {
        InputStream inputStream = new ClassPathResource(filePath).getInputStream();
        BufferedInputStream in = new BufferedInputStream(inputStream);
        ResponseUtil.asFile(response, outputFileName);
        OutputStream os = response.getOutputStream();
        byte[] dataBuffer = new byte[1024];
        int len;
        while ((len = in.read(dataBuffer)) != -1) {
            os.write(dataBuffer, 0, len);
        }
    }
}
