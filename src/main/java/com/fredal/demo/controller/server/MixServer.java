package com.fredal.demo.controller.server;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping
public class MixServer {
    // ignore_security_alert
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile file)
    {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        String storePath= System.getProperty("user.dir")+"/file/";
        File destFile = new File(storePath + "/" + fileName);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();//注意，是mkdirs不是mkdir，后者只创建文件的直接父目录，创建不了多层目录
        }
        try {
            file.transferTo(destFile);
            return destFile.getAbsolutePath();//返回文件上传成功后的文件存储完整路径
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        // 路径可以指定当前项目相对路径
        File file = new File(System.getProperty("user.dir")+"/file/" + fileName);// ignore_security_alert
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            // 如果文件名为中文需要设置编码
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf8"));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }
    }
}
