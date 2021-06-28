package com.blog.controller.blog;

import cn.hutool.json.JSONUtil;
import com.blog.commons.Result;
import com.blog.pojo.TbBlog;
import com.blog.service.AdminService;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springboot
 * @ClassName: BlogController
 * @Author: jian
 * @Description:
 * @Date: 2021/5/26 17:18
 * @Version: 1.0
 */
@Controller
public class BlogController {


   @Autowired
   private AdminService adminService;
   @Autowired
   private ArticleService articleService;

    @GetMapping({"","/","index"})
    private ModelAndView index(HttpServletRequest request,
                               HttpServletResponse response){
        Map<String, String> config = adminService.getWebSite();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blog/index");
        mv.addObject("logo","jian");
        mv.addObject("webSiteConfig",config);
        mv.addObject("copyRight",config.get("footerCopyRight"));
        mv.addObject("allBlog",articleService.getAllBlog());
        mv.addObject("hotBlog",articleService.getHotBlog());
        return mv;
    }

    /**
     * 组装返回数据
     * @return
     */
    @PostMapping("getArticleList")
    @ResponseBody
    private Result getIndex(){
        List<TbBlog> allBlog = articleService.getAllBlog();
        return new Result(0,"成功",allBlog);
    }

    @GetMapping("toBlog/{id}")
    private ModelAndView toBlog(@PathVariable("id") Integer id,HttpServletRequest request,
                          HttpServletResponse response) throws ParseException {
         TbBlog tbBlog = articleService.findById(id);
        Map<String, String> webSite = adminService.getWebSite();
        //更新观看人数
        articleService.updateBlogViews(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blog/blog");
        mv.addObject("blog",tbBlog);
        mv.addObject("copyRight",webSite.get("footerCopyRight"));
        mv.addObject("content", tbBlog.getBlogContent());
        return mv;
    }
    @GetMapping("getBlog/{id}")
    @ResponseBody
    private Result toBlog(@PathVariable("id") Integer id){
        TbBlog tbBlog = articleService.findById(id);
        return new Result(0,"成功",tbBlog);
    }
    @GetMapping("article")
    private String toArticle(){
        return "/blog/article";
    }
    @GetMapping("read")
    private String toRead(){
        return "/blog/read";
    }
}
