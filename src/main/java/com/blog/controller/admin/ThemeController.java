package com.blog.controller.admin;

import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.TbBlogTheme;
import com.blog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: dal-blog
 * @description: 主题
 * @author: jian
 * @create: 2021-07-03 15:44
 **/
@Controller
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX + "theme")
public class ThemeController {

    @Autowired
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
