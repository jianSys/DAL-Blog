package com.blog.commons.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ProjectName: dal-blog
 * @ClassName: FileUtil
 * @Author: jian
 * @Description: 上传工具类
 * @Date: 2021/7/1 17:06
 * @Version: 1.0
 */
public class FileUtil {
    /**
     * 功能描述: 处理文件上传
     * @Param: [file  文件, path 文件存储路径, fileName 原文件名]
     * @Return: java.lang.String
     * @Author: Sichengluis
     * @Date: 2021/2/18 12:03
     */
    public static String save(MultipartFile file, String path, String fileName){

        //获取上传的名字
        String filename = file.getOriginalFilename();
        //截取后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //生成新的文件名
        String newFileName = UUID.randomUUID().toString() + suffix;

        // 生成新的文件名
        String realPath = path + newFileName;

        //使用原文件名
//        String realPath = path + "/" + fileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return newFileName;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}
