package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.TbBlog;
import com.blog.service.PageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: PageController
 * @Author: jian
 * @Description: 页面操作
 * @Date: 2021/6/29 15:47
 * @Version: 1.0
 */
@Log4j2
@RestController
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX + "page")
public class PageController extends BaseController {

    public static String MODULE_PATH = "/admin/page/";

    @Resource
    private PageService pageService;

    @GetMapping("toPageAdd")
    private ModelAndView toPageAdd() {
        return jumpPage(MODULE_PATH + "pageAdd");
    }

    @GetMapping("toPageForm")
    private ModelAndView toPageForm() {
        return jumpPage(MODULE_PATH + "pageForm");
    }


    @GetMapping("toPageList")
    private ModelAndView toPageList() {
        return jumpPage(MODULE_PATH + "pageList");
    }


    @PostMapping("savePage")
    @ResponseBody
    private Result saveArticle(@RequestBody TbBlog tbBlogEntity) {
        log.info("===============保存页面的入参为===========[{}]", JSONUtil.parse(tbBlogEntity));
        try {
            pageService.savePage(tbBlogEntity);
            return Result.ok("保存页面信息成功");
        } catch (Exception e) {
            log.error("=====================保存页面信息异常=============", e);
            return Result.error("保存页面信息失败");
        }
    }

    @ResponseBody
    @GetMapping("getPageList")
    private Result getAllPage(
            PageDomain domain
    ) {
        List<TbBlog> all = pageService.getAllPage();
        return new Result(0, "查询所有页面成功", all);
    }
}
