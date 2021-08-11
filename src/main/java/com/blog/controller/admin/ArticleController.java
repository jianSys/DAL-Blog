package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
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
import org.springframework.web.servlet.ModelAndView;

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
@RestController
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX + "article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    public static String MODULE_PATH = "/admin/article/";

    /**
     * 跳转到文章列表页面
     *
     * @return
     */
    @GetMapping("toArticleList")
    private ModelAndView toArticleList() {
        return jumpPage(MODULE_PATH + "articleList");
    }

    /**
     * 新增文章页面
     *
     * @return
     */
    @GetMapping("toArticleEdit")
    private ModelAndView toArticleListFrom() {
        return jumpPage(MODULE_PATH + "articleEdit");
    }

    /**
     * 新增文章页面
     *
     * @return
     */
    @GetMapping("toListForm")
    private ModelAndView toListFrom() {
        return jumpPage(MODULE_PATH + "listForm");
    }


    @GetMapping("toArticleAdd")
    private ModelAndView toArticleListAdd() {
        return jumpPage(MODULE_PATH + "articleAdd");
    }

    /**
     * 分页查询所有文章
     * @param domain
     * @param blog
     * @return
     */
    @ResponseBody
    @GetMapping("articleList")
    private Result articleList(PageDomain domain,
                               TbBlog blog) {
        log.info("分页查询文章列表的入参为==========>>>>[{},{},{}]", domain.getPage(), domain.getLimit(), blog);try {
            PageResult<TbBlog> byPage = articleService.findByPage(domain, blog);
            Long total = byPage.getTotal();
            List<TbBlog> content = byPage.getData();
            Result result = new Result(0, "成功", total.intValue(), content);
            return result;
        } catch (Exception e) {
            log.error("查询文章列表异常==============", e);
            return Result.error("查询文章列表失败");
        }
    }

    @GetMapping("categoryList")
    @ResponseBody
    private Result categoryList(PageDomain domain) {
        Pageable pageable = new PageRequest(domain.getPage() - 1, domain.getLimit());
        try {
            PageResult page = articleService.findCategoryByPage(pageable);
            List<TbBlogCategory> content = page.getData();
            return new Result(0, "成功", content.size(), content);
        } catch (Exception e) {
            log.error("查询分类列表异常", e);
            return Result.error("失败");

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
        log.info("===============保存文章的入参为===========[{}]", JSONUtil.parse(tbBlogEntity));
        try {
            TbBlog save = articleService.save(tbBlogEntity);
            return Result.ok("成功");
        } catch (Exception e) {
            log.error("=====================保存文章信息异常=============", e);
            return Result.error("失败");
        }
    }

    @DeleteMapping("delArticle/{blogId}")
    @ResponseBody
    private Result delArticleById(@PathVariable("blogId") Integer id) {
        log.info("删除文章的入参为[{}]", id);
        try {
            articleService.delArticleById(id);
            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error("删除文章异常", e);
            return Result.error("删除失败");
        }
    }
}
