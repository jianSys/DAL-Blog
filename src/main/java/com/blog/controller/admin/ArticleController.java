package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.Result;
import com.blog.pojo.TbBlogCategoryEntity;
import com.blog.pojo.TbBlogEntity;
import com.blog.pojo.TbBlogTagEntity;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ProjectName: springboot
 * @ClassName: ArticleController
 * @Author: jian
 * @Description: 文章管理
 * @Date: 2021/5/27 14:45
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /**
     * 跳转到文章列表页面
     * @return
     */
    @GetMapping("toArticleList")
    private String toArticleList(){
        return "/admin/article/articleList";
    }

    /**
     * 新增文章页面
     * @return
     */
    @GetMapping("toArticleEdit")
    private String toArticleListFrom(){return "/admin/article/articleEdit";}

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param id
     * @param title
     * @param blogCategoryName
     * @return
     */
    @ResponseBody
    @GetMapping("articleList")
    private Result articleList(@RequestParam(value = "page",required = true) Integer page,
                               @RequestParam(value = "limit",required = true) Integer limit,
                               @RequestParam(value = "id",required = false) Integer id,
                               @RequestParam(value = "title",required = false) String title,
                               @RequestParam(value = "blogCategoryName",required = false)String blogCategoryName){
        //创建查询条件
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("blogCategoryName",blogCategoryName);
        Sort sort = new Sort(Sort.Direction.DESC,"blogId");
        Pageable pageable = new PageRequest(page-1,limit);
        Page<TbBlogEntity> byPage = articleService.findByPage(map, pageable);
        List<TbBlogEntity> content = byPage.getContent();
        Result result = new Result(0, "成功", content.size(), content);
        return result;
    }

    @GetMapping("categoryList")
    @ResponseBody
    private Result categoryList(@RequestParam(value = "page",required = true) Integer page,
                            @RequestParam(value = "limit",required = true) Integer limit){
        Pageable pageable = new PageRequest(page-1,limit);
        Page<TbBlogCategoryEntity> category = articleService.findCategoryByPage(pageable);
        List<TbBlogCategoryEntity> content = category.getContent();
        System.out.println(content);
        return new Result(0,"成功",content.size(),content);
    }
    @GetMapping("getCategory")
    @ResponseBody
    private Result getCategory(){
        List<TbBlogCategoryEntity> allCategory = articleService.findAllCategory();

        return new Result(0,"成功",allCategory.size(),allCategory);
    }

    @PostMapping("saveArticle")
    @ResponseBody
    private Result saveArticle(@RequestBody TbBlogEntity tbBlogEntity){
        TbBlogEntity save = articleService.save(tbBlogEntity);
        return new Result(0,"成功",save);
    }
    @DeleteMapping("delArticle/{blogId}")
    @ResponseBody
    private Result delArticleById(@PathVariable("blogId")Integer id){
        System.out.println("================"+id+"=====================");
        articleService.delArticleById(id);
        return new Result(0,"成功");
    }
}
