package com.bluebird.controller;

import com.bluebird.po.Result;
import com.bluebird.utils.AliOSSUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils fileUtils;

    //本地上传
    /*@PostMapping("/upload")
    public Result upload(String username,Integer age,@RequestParam("image") MultipartFile file) throws Exception {
        log.info("文件上传:{},{},{}",username,age,file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取字符串最后一次出现的索引位置
        int index = originalFilename.lastIndexOf(".");
        //字符串截取
        String extname = originalFilename.substring(index);
        //构造通用唯一识别码,拼接截取的文件后缀
        String newFileName= UUID.randomUUID().toString()+extname;
        log.info("新的文件名:{}",newFileName);
        //保存文件在本地磁盘下
        file.transferTo(new File("F:\\images\\"+newFileName));
        return Result.success();
    }*/

    //云端上传
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，{}", image.getOriginalFilename());
        //调用阿里云oss工具类进行文件上传
        String url = fileUtils.upload(image);
        log.info("文件上传完成，url:{}", url);
        return Result.success(url);
    }
}
