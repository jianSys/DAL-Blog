package com.blog.controller.admin;

import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.TbBlogTheme;
import com.blog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: dal-blog
 * @description: 主题
 * @author: jian
 * @create: 2021-07-03 15:44
 **/
@RestController
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX + "theme")
public class ThemeController extends BaseController {


    public static String MODULE_PATH = "/admin/theme/";

    @Resource
    private ThemeService themeService;

    @GetMapping("toThemeList")
    private ModelAndView toThemeList() {
        List<TbBlogTheme> themeList = themeService.getAllTheme();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/theme/themeList");
        mv.addObject("themeList", themeList);
        return mv;
    }

    @ResponseBody
    @GetMapping("replaceTheme/{id}")
    private Result replaceTheme(@PathVariable("id") Integer id) {
        themeService.replaceTheme(id);
        return new Result(0, "修改成功");
    }
}
