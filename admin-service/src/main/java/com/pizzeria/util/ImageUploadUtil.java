package com.pizzeria.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUploadUtil {

    private static final String UPLOAD_DIR = "uploads/";

    public static String saveImage(MultipartFile file){
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            String filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(filePath);
            file.transferTo(path);
            return filePath;
        } catch (IOException e){
            throw new RuntimeException("Could not save image: " + e.getMessage());
        }
    }
}
