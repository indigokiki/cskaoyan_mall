package com.cskaoyan.service.mallmanage;

import com.cskaoyan.bean.Result;
import org.springframework.web.multipart.MultipartFile;

public interface PicService {
    Result create(MultipartFile file);
}
