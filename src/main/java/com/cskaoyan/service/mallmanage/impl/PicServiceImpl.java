package com.cskaoyan.service.mallmanage.impl;

import com.cskaoyan.bean.Picture;
import com.cskaoyan.bean.Result;
import com.cskaoyan.mapper.CskaoyanMallStorageMapper;
import com.cskaoyan.service.mallmanage.PicService;
import com.cskaoyan.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PicServiceImpl implements PicService {

    @Autowired
    CskaoyanMallStorageMapper storageMapper;

    @Override
    public Result create(MultipartFile file) {
        Picture picture = null;
        try {
            picture = FileUpload.pictureUpload(file);
            Integer id = storageMapper.getMaxId();
            if (id == null){
                picture.setId(1);
            }else {
                picture.setId(id + 1);
            }
            picture.setUrl("http://localhost:80/admin/wx/storage/fetch/" + picture.getKey());
            int insertPicture = storageMapper.insertPicture(picture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setData(picture);
        result.setErrno(0);
        result.setErrmsg("成功");
        return result;
    }
}
