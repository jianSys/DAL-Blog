package com.blog.controller.admin;

import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.TbBlogCategory;
import com.blog.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
@RestController
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX + "category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    public static String MODULE_PATH = "/admin/category/";

    @GetMapping("toCategoryList")
    private ModelAndView toCategoryList() {
        return jumpPage(MODULE_PATH + "categoryList");
    }

    @GetMapping("getAllCategory")
    @ResponseBody
    private Result getAllCategory() {
        List<TbBlogCategory> allCategory = categoryService.findAllCategory();
        return new Result(0, "成功", allCategory.size(), allCategory);
    }

    @GetMapping("toCategoryEdit")
    private ModelAndView toCategoryEdit() {
        return jumpPage(MODULE_PATH + "categoryEdit");
    }


    /**
     * find category list by page
     *
     * @param domain
     * @return
     */
    @GetMapping("categoryList")
    @ResponseBody
    private Result categoryList(PageDomain domain) {
        try {
            PageResult<TbBlogCategory> category = categoryService.findCategoryByPage(domain);
            Long total = category.getTotal();
            List<TbBlogCategory> content = category.getData();
            return new Result(0, "成功", total.intValue(), content);
        } catch (Exception e) {
            log.error("查询分类列表异常", e);
            return Result.error("失败");

        }
    }

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
