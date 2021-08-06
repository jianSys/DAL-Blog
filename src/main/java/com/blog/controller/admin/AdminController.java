package com.blog.controller.admin;

import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.TbAdminUser;
import com.blog.pojo.TbBlogConfig;
import com.blog.pojo.vo.BlogLogVO;
import com.blog.service.AdminService;
import com.blog.service.ArticleService;
import com.blog.service.LogService;
import com.blog.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dal-blog
 * @ClassName: AdminController
 * @Author: jian
 * @Description:
 * @Date: 2021/6/3 16:59
 * @Version: 1.0
 */
@Log4j2
@RestController
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX)
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;


    public static String MODULE_PATH = "/admin/system/";

    @GetMapping({"", "/", "/index", "/index.ftl"})
    private ModelAndView toIndex(HttpServletRequest request,
                                 HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("loginUser", request.getSession().getAttribute("loginUser"));
        return jumpPage("/admin/index", map);
    }

    @GetMapping("toTool")
    private ModelAndView toTool() {
        return jumpPage(MODULE_PATH + "tool");
    }

    @GetMapping("toPassword")
    private ModelAndView toPassword() {
        return jumpPage(MODULE_PATH + "password");
    }

    @GetMapping("toWebSite")
    private ModelAndView toWebSite() {
        return jumpPage(MODULE_PATH + "website");
    }


    @GetMapping("toUserInfo")
    private ModelAndView toUserInfo() {
        return jumpPage(MODULE_PATH + "userInfo");
    }

    @ResponseBody
    @GetMapping("getUserInfo")
    private Result getUserInfo(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("loginUser");
        TbAdminUser userInfo = userService.getUserInfo(username);
        return Result.ok("获取用户成功", userInfo);
    }

    @ResponseBody
    @PostMapping("saveSite")
    private Result webSite(@RequestBody Map<String, Object> map) {
        log.info("修改网站设置的入参为======================[{}]", map);
        try {
            List<TbBlogConfig> configs = adminService.saveWebSite(map);
            return Result.ok("成功");
        } catch (Exception e) {
            log.error("==============修改网站设置失败===========", e);
            return Result.error("失败");

        }
    }

    @ResponseBody
    @GetMapping("getWebSite")
    private Result getWebSite() {
        log.info("=======================开始查询所有网站设置===================");
        try {
            Map<String, String> webSite = adminService.getWebSite();
            return new Result(0, "成功", webSite);
        } catch (Exception e) {
            log.error("======================查询网站设置异常=====================", e);
            return new Result(500, "查询失败");
        }
    }

    /**
     * 初始化导航页
     *
     * @return
     */
    @GetMapping("home")
    private ModelAndView toHomePage2() {
        Map<String, Object> map = new HashMap<>();
        map.put("viewsCount", articleService.getArticleViewsCount());
        map.put("articleCount", articleService.getArticleCount());
        map.put("logs", logService.getLatestLog());
        return jumpPage("/admin/home/home", map);
    }
}
