package com.blog.controller.admin;

import com.blog.commons.Result;
import com.blog.pojo.TbBlogCategoryEntity;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: springboot
 * @ClassName: CategoryController
 * @Author: jian
 * @Description: article category
 * @Date: 2021/6/3 9:51
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("toCategoryList")
    private String toCategoryList(){return "/admin/category/categoryList";}

    @GetMapping("toCategoryEdit")
    private String toCategoryEdit(){return "/admin/category/categoryEdit";}


    /**
     *  find category list by page
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("categoryList")
    @ResponseBody
    private Result categoryList(@RequestParam(value = "page",required = true) Integer page,
                                @RequestParam(value = "limit",required = true) Integer limit){
        Pageable pageable = new PageRequest(page-1,limit);
        Page<TbBlogCategoryEntity> category = categoryService.findCategoryByPage(pageable);
        List<TbBlogCategoryEntity> content = category.getContent();
        System.out.println(content);
        return new Result(0,"成功",content.size(),content);
    }

    /**
     * Save the article category
     * @param categoryEntity
     * @return
     */
    @PostMapping("saveCategory")
    @ResponseBody
    private Result saveCategory(@RequestBody TbBlogCategoryEntity categoryEntity){
        return null;
    }
}
