package com.ac.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Jbee on 2016. 11. 22..
 */
public class FileUploadUtils {
    public static final String FILE_PATH = "/Users/Naver/angryconsumer_image";

    public static final String fileUpload(MultipartFile uploadFile) {
        if(uploadFile == null) {
            return "Not Found File!";
        }
        InputStream inputStream;
        OutputStream outputStream;

        String fileName = uploadFile.getOriginalFilename();

        File file = new File(FILE_PATH + fileName);
        try {
            inputStream = uploadFile.getInputStream();
            file.createNewFile();
            outputStream = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}