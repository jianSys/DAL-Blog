package com.blog.controller.admin;

import com.blog.commons.Result;
import com.blog.pojo.TbBlogCategory;
import com.blog.service.CategoryService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("toCategoryList")
    private String toCategoryList() {
        return "/admin/category/categoryList";
    }

    @GetMapping("toCategoryEdit")
    private String toCategoryEdit() {
        return "/admin/category/categoryEdit";
    }


    /**
     * find category list by page
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("categoryList")
    @ResponseBody
    private Result categoryList(@RequestParam(value = "page", required = true) Integer page,
                                @RequestParam(value = "limit", required = true) Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        try {
            Page<TbBlogCategory> category = categoryService.findCategoryByPage(pageable);
            Long total = category.getTotalElements();
            List<TbBlogCategory> content = category.getContent();
            return new Result(0, "成功", total.intValue(), content);
        }catch (Exception e){
            log.error("查询分类列表异常",e);
            return new Result(500, "失败");

        } }

    /**
     * Save the article category
     *
     * @param categoryEntity
     * @return
     */
    @PostMapping("saveCategory")
    @ResponseBody
    private Result saveCategory(@RequestBody TbBlogCategory categoryEntity) {
        return null;
    }
}
