package com.ac.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Jbee on 2016. 11. 22..
 */
public class FileUploadUtils {
    public static final String fileUpload(MultipartFile uploadFile) {
        if(uploadFile == null) {
            return "Not Found File!";
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;

        String fileName = uploadFile.getOriginalFilename();

        File file = new File("./src/main/resources/static/uploadImage/" + fileName);//path
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
        return file.getAbsolutePath();
    }
}
