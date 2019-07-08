package com.cskaoyan.util;

import com.cskaoyan.bean.Picture;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class FileUpload {

    public static Picture pictureUpload(MultipartFile file) throws IOException {
        Picture picture = new Picture();
        String filename = file.getOriginalFilename();
        File directory = new File("C://fileupload");
        if (!directory.exists()){
            directory.mkdirs();
        }
        String name =  UUID.randomUUID() + ".jpg";
        name = name.replace("-", "");
        File myfile = new File(directory, name);
        file.transferTo(myfile);
        picture.setName(filename);
        picture.setKey(name);
        picture.setType(file.getContentType());
        picture.setSize(file.getSize());
        picture.setAddTime(new Date());
        picture.setUpdateTime(new Date());
        return picture;

    }
}
