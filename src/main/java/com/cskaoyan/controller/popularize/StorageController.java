//package com.cskaoyan.controller.popularize;
//
//import com.cskaoyan.bean.popularize.Storage;
//import com.cskaoyan.mapper.popularize.StorageMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("admin")
//public class StorageController {
//    @Autowired
//    StorageMapper storageMapper;
//    @ResponseBody
//    @RequestMapping("/storage/create")
//    public Map<String, Object> upload(MultipartFile file, HttpServletRequest request) {
//        String path = AdController.class.getClassLoader().getResource("").getPath();
//        System.out.println(path);
//        File dir=new File(path+"wx/storage/fetch/");
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
//        Storage storage = new Storage();
//        String fileName=file.getOriginalFilename();
//        storage.setName(fileName);
//        String uuid= UUID.randomUUID().toString().replace("-","");
//        fileName=uuid+"_"+fileName;
//        storage.setAddTime(new Date());
//        storage.setUpdateTime(new Date());
//        storage.setKey(fileName);
//        storage.setKey("123");
//        storage.setSize((int) file.getSize());
//        storage.setUrl("http://localhost/admin/wx/storage/fetch/"+fileName);
//        storage.setType(file.getContentType());
//        storage.setDeleted(true);
//        int insert = storageMapper.insert(storage);
//        if(insert==1){
//
//            Map<String, Object> map = new HashMap<>();
//            try {
//                file.transferTo(new File(dir,fileName));
//                map.put("errmsg", "成功");
//                map.put("errno", 0);
//                map.put("data",storage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return map;
//        }
//        return null;
//    }
//
//}
