package com.blog.controller.admin;

import cn.hutool.json.JSONObject;
import com.blog.commons.MessageConstant;
import com.blog.commons.Result;
import com.blog.commons.utils.BlogUtil;
import com.blog.pojo.TbBlog;
import com.blog.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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





   /* @ResponseBody
    @PostMapping( "uploadFile")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){
        JSONObject jsonObject=new JSONObject();

        try {
            //获取上传的名字
            String filename = attach.getOriginalFilename();
            //截取后缀名
            String suffix = filename.substring(filename.lastIndexOf("."));
            //Random r = new Random();
            //生成新的文件名
            String newFileName = UUID.randomUUID().toString() + suffix;

            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            //获取到c盘下一个tomcat路径
            String rootPath = request.getSession().getServletContext().getRealPath("/resource/img");
            //获取项目路径
            String property = System.getProperty("user.dir");
*//*
            System.out.println("editormd上传图片："+property);

            String path = "/Users/jian/Desktop/IO/";*//*
            File file = new File("/opt/deploy/upload/" + newFileName);
            String fileUrl = BlogUtil.getHost(new URI(request.getRequestURL() + "")) + "/upload/" + newFileName;

            *//**
             * 文件路径不存在则需要创建文件路径
             *//*
            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }

            // 最终文件名
           // File realFile = new File(filePath + File.separator + newFileName);
            //Files.copy(attach.getInputStream(),realFile.toPath());
            //FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);
            attach.transferTo(file);
            // 下面response返回的json格式是editor.md所限制的，规范输出就OK
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", fileUrl);
        } catch (Exception e) {
            jsonObject.put("success", 0);
        }
        return jsonObject.toString();
    }

*/
    /**
     * md文档内图片
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("uploadFile")
    public void uploadFileByEditormd(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(name = "editormd-image-file", required = true)
                                             MultipartFile file) throws IOException, URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        File destFile = new File(MessageConstant.FILE_UPLOAD_DIC + newFileName);
        String fileUrl = BlogUtil.getHost(new URI(request.getRequestURL() + "")) + "/upload/" + newFileName;
        File fileDirectory = new File(MessageConstant.FILE_UPLOAD_DIC);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            response.getWriter().write("{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}");
        } catch (UnsupportedEncodingException e) {
            response.getWriter().write("{\"success\":0}");
        } catch (IOException e) {
            response.getWriter().write("{\"success\":0}");
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
