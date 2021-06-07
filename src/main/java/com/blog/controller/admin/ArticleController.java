package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.Result;
import com.blog.pojo.TbBlogCategory;
import com.blog.pojo.TbBlog;
import com.blog.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: springboot
 * @ClassName: ArticleController
 * @Author: jian
 * @Description: 文章管理
 * @Date: 2021/5/27 14:45
 * @Version: 1.0
 */
@Log4j2
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 跳转到文章列表页面
     *
     * @return
     */
    @GetMapping("toArticleList")
    private String toArticleList() {
        return "/admin/article/articleList";
    }

    /**
     * 新增文章页面
     *
     * @return
     */
    @GetMapping("toArticleEdit")
    private String toArticleListFrom() {
        return "/admin/article/articleEdit";
    }

    /**
     * 新增文章页面
     *
     * @return
     */
    @GetMapping("toListForm")
    private String toListFrom() {
        return "/admin/article/listform";
    }

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param id
     * @param title
     * @param blogCategoryName
     * @return
     */
    @ResponseBody
    @GetMapping("articleList")
    private Result articleList(@RequestParam(value = "page", required = true) Integer page,
                               @RequestParam(value = "limit", required = true) Integer limit,
                               @RequestParam(value = "id", required = false) Integer id,
                               @RequestParam(value = "title", required = false) String title,
                               @RequestParam(value = "blogCategoryName", required = false) String blogCategoryName) {
        log.info("分页查询文章列表的入参为==========>>>>[{},{},{},{},{}]",page,limit,id,title,blogCategoryName);
        //创建查询条件
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("blogCategoryName", blogCategoryName);
        Sort sort = new Sort(Sort.Direction.DESC, "blogId");
        Pageable pageable = new PageRequest(page - 1, limit);
        try{
            Page<TbBlog> byPage = articleService.findByPage(map, pageable);
            List<TbBlog> content = byPage.getContent();
            Result result = new Result(0, "成功", content.size(), content);
            return result;
        }catch (Exception e){
            log.error("查询文章列表异常==============",e);
            Result result = new Result(500, "失败");
            return result;
        }
    }

    @GetMapping("categoryList")
    @ResponseBody
    private Result categoryList(@RequestParam(value = "page", required = true) Integer page,
                                @RequestParam(value = "limit", required = true) Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        try {
            Page<TbBlogCategory> category = articleService.findCategoryByPage(pageable);
            List<TbBlogCategory> content = category.getContent();
            return new Result(0, "成功", content.size(), content);
        }catch (Exception e){
            log.error("查询分类列表异常",e);
            return new Result(500, "失败");

        }
    }

    @GetMapping("getCategory")
    @ResponseBody
    private Result getCategory() {
        List<TbBlogCategory> allCategory = articleService.findAllCategory();
        return new Result(0, "成功", allCategory.size(), allCategory);
    }

    @PostMapping("saveArticle")
    @ResponseBody
    private Result saveArticle(@RequestBody TbBlog tbBlogEntity) {
        log.info("保存文章的入参为===========[{}]",JSONUtil.parse(tbBlogEntity));
        try{
            TbBlog save = articleService.save(tbBlogEntity);
            return new Result(0, "成功", save);
        }catch (Exception e){
            log.error("保存文章信息异常",e);
            return new Result(500, "失败");
        }
    }

    @DeleteMapping("delArticle/{blogId}")
    @ResponseBody
    private Result delArticleById(@PathVariable("blogId") Integer id) {
        log.info("删除文章的入参为[{}]",id);
        try{
            articleService.delArticleById(id);
            return new Result(0, "成功");
        }catch (Exception e){
            log.error("删除文章异常",e);
            return new Result(500, "失败");
        }

    }
}
