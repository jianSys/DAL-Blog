package com.blog.controller.blog;

import com.blog.commons.utils.IpUtil;
import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.commons.web.domain.response.Result;
import com.blog.pojo.TbBlog;
import com.blog.pojo.vo.BlogVO;
import com.blog.service.AdminService;
import com.blog.service.ArticleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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
@RequestMapping
public class BlogController {
    /**
     * 默认主题
     */
    private static String theme = "amaze";

    @Autowired
    private AdminService adminService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询主题
     */
    public void getRedisTemplate() {
        String t = (String) redisTemplate.opsForValue().get("theme");
        if (StringUtils.isNotBlank(t)) {
            theme = t;
        }
    }

    @GetMapping({"", "/", "index"})
    private ModelAndView index() {
        return this.page(1);
    }

    @GetMapping("page/{PageNum}")
    private ModelAndView page(@PathVariable("pageNum")int pageNum){
        getRedisTemplate();
        Map<String, String> config = adminService.getWebSite();
        PageResult<BlogVO> result = articleService.getPageBlog(pageNum);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blog/" + theme + "/index");
        mv.addObject("config", config);
        mv.addObject("copyRight", config.get("footerCopyRight"));
        mv.addObject("allBlog", result.getData());
        mv.addObject("page",result);
        mv.addObject("currentPage" ,pageNum);
        mv.addObject("hotBlog", articleService.getHotBlog());
        return mv;
    }

    /**
     * 组装返回数据
     *
     * @return
     */
    @PostMapping("getArticleList")
    @ResponseBody
    private Result getIndex() {
        List<BlogVO> allBlog = articleService.getAllBlog();
        return new Result(0, "成功", allBlog);
    }

    @GetMapping("toBlog/{id}")
    private ModelAndView toBlog(@PathVariable("id") Integer id, HttpServletRequest request,
                                HttpServletResponse response) throws ParseException {
        getRedisTemplate();
        ModelAndView mv = new ModelAndView();
        BlogVO tbBlog = articleService.getBlogById(id);
        if (null == tbBlog) {
            mv.setViewName("/error/404");
            return mv;
        }
        //更新观看人数
        articleService.updateBlogViews(id);
        mv.setViewName("/blog/" + theme + "/blog");
        mv.addObject("blog", tbBlog);
        mv.addObject("config", adminService.getWebSite());
        return mv;
    }

    @GetMapping("getBlog/{id}")
    @ResponseBody
    private Result toBlog(@PathVariable("id") Integer id) {
        TbBlog tbBlog = articleService.findById(id);
        return new Result(0, "成功", tbBlog);
    }

    @GetMapping("s/{url}")
    private ModelAndView getPage(HttpServletRequest request, @PathVariable("url") String url) {
        getRedisTemplate();
        BlogVO tbBlog = articleService.getPageByUrl(url);
        Map<String, String> webSite = adminService.getWebSite();
        //更新观看人数
        articleService.updateBlogViews(tbBlog.getBlogId());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blog/" + theme + "/blog");
        mv.addObject("blog", tbBlog);
        mv.addObject("config", webSite);
        return mv;
    }

    @GetMapping("article")
    private String toArticle() {
        return "/blog/" + theme + "/article";
    }

    @GetMapping("read")
    private String toRead() {
        return "/blog/" + theme + "/read";
    }

    /**
     * 首页归档数据返回
     * @return
     */
    @GetMapping("archive")
    private ModelAndView archive(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("blog",articleService.getArchiveBlog());
        mv.addObject("config", adminService.getWebSite());
        mv.setViewName("/blog/" + theme + "/archive");
        return mv;
    }
}
