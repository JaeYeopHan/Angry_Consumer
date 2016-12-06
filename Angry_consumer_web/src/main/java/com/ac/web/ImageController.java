package com.ac.web;

import com.ac.util.FileUploadUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Jbee on 2016. 11. 30..
 */
@Controller
public class ImageController {
    @RequestMapping(value = "/uploadImage/{filePath}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable String filePath) throws IOException {
        String imagePath = FileUploadUtils.FILE_PATH + filePath + ".jpg";
        Path p = Paths.get(imagePath);
        byte[] image = Files.readAllBytes(p);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
