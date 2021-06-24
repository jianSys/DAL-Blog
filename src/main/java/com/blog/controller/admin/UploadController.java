package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.Result;
import com.blog.pojo.TbBlog;
import com.blog.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.util.datetime.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: springboot
 * @ClassName: UploadController
 * @Author: jian
 * @Description: File upload management
 * @Date: 2021/6/3 11:24
 * @Version: 1.0
 */
@Log4j2
@Controller
@RequestMapping("/admin/upload")
public class UploadController {


    @Value("${blog.upload.dir}")
    private String filePath;

    @Value("${blog.upload.file.dir}")
    private String fileDirPath;

    @Autowired
    private ArticleService articleService;

    @PostMapping("images")
    @ResponseBody
    private Result uploadImages(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        log.info("==============上传照片的入参为=================",file);
        //上传到指定目录下
        File dir = new File(filePath);
        //文件夹不存在创建文件夹
        if (!dir.exists() && !dir.isDirectory()) {
            //创建文件夹
            dir.mkdir();
        }
        //获取上传的名字
        String filename = file.getOriginalFilename();
        //截取后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //生成新的文件名
        String newFileName = UUID.randomUUID().toString() + suffix;
        //构造新的图片文件
        File newFile = new File(dir, newFileName);
        System.out.println(suffix);
        //把图片保存在本地
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            log.error("=========保存图片异常============",e);
            e.printStackTrace();
        }
//        生成图片的url
        String imgUrl = filePath + newFileName;
        System.out.println(imgUrl);
        return new Result(0, "成功", imgUrl);
    }

    @PostMapping("file")
    @ResponseBody
    private Result uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String str="";
        String filename = file.getOriginalFilename();
        try {
            InputStream ins = null;

            ins = file.getInputStream();

            File toFile = new File(filename);

            inputStreamToFile(ins, toFile);

            FileReader fr = new FileReader(toFile);

            BufferedReader br = new BufferedReader(fr);

            String line="";

            int lineNum=0;

            while((line=br.readLine())!=null /*&& Format.isNotNull(line)*/){
                //替换字符
                //String replace = line.replace("\n", "你在干什么");
                str = str + line;
                System.out.println(line);
            }

            br.close();

            fr.close();

        } catch (Exception e) {
            log.error("splitText e:" + e);
        }
        TbBlog tbBlog = new TbBlog();
        tbBlog.setBlogTitle(filename);
        tbBlog.setBlogContent(str);
        tbBlog.setBlogStatus(0);
        tbBlog.setEnableComment(0);
        tbBlog.setBlogTop(0);
        articleService.save(tbBlog);
        return new Result(0, "成功");
    }

    public static void inputStreamToFile(InputStream ins, File file) {

        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //上传文件到本地
    public static void main(String[] args) throws Exception {

        String lineTxt = null;

        File file = new File("E://redis.md");
        String encoding="UTF-8";

        File path=new File("F://redis.md");

        if(file.isFile() && file.exists()){ //判断文件是否存在

            InputStreamReader read = new InputStreamReader(

                    new FileInputStream(file),encoding);//考虑到编码格式

            BufferedReader bufferedReader = new BufferedReader(read);

            //bufferedReader.
            while((lineTxt = bufferedReader.readLine()) != null){
                //str = str +
                //list.add(Integer.parseInt(lineTxt));
                System.out.println(lineTxt);
            }
            read.close();
        }
    }
}
