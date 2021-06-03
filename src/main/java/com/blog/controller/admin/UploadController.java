package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ProjectName: springboot
 * @ClassName: UploadController
 * @Author: jian
 * @Description: File upload management
 * @Date: 2021/6/3 11:24
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin/upload")
public class UploadController {

    @Value("${blog.upload.dir}")
    private String filePath;

    @PostMapping("images")
    @ResponseBody
    private Result uploadImages(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //上传到指定目录下
        File dir = new File(filePath);
        //文件夹不存在创建文件夹
        if (!dir.exists() && !dir.isDirectory()){
            //创建文件夹
            dir.mkdir();
        }
        //获取上传的名字
        String filename = file.getOriginalFilename();
        //截取后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //生成新的文件名
        String newFileName = UUID.randomUUID().toString()+suffix;
        //构造新的图片文件
        File newFile = new File(dir,newFileName);
        System.out.println(suffix);
        //把图片保存在本地
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        生成图片的url
        String imgUrl = filePath+newFileName;
        System.out.println(imgUrl);
        return new Result(0,"成功",imgUrl);
    }
}
