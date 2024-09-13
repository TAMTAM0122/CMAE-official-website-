package com.cmae.chairman.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/Tool")
public class ToolController {

    @PostMapping("/Upload")
    public String uploadImage(@RequestParam("type") String type, @RequestParam("file") MultipartFile file) {
        String uploadDir = "D:/uploads/" + type + "/";

        // 确保文件夹存在
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 获取文件名并添加时间戳防止重复
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String newFileName = timeStamp + "_" + fileName;

        // 保存文件
        try {
            Path path = Paths.get(uploadDir + newFileName);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }

        return type + "/" + newFileName;
    }

}
