package com.blog.controller.admin;

import com.blog.commons.Result;
import com.blog.dao.BlogLogDao;
import com.blog.pojo.TbAdminUser;
import com.blog.pojo.TbBlogConfig;
import com.blog.pojo.TbBlogLog;
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
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @GetMapping({"", "/", "/index", "/index.ftl"})
    private ModelAndView toIndex(HttpServletRequest request,
                                 HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/index");
        //mv.addObject("blogCount",articleService.getArticleCount());
        mv.addObject("loginUser",request.getSession().getAttribute("loginUser"));
        return mv;
    }

    @GetMapping("toTool")
    private String toTool() {
        return "/admin/system/tool";
    }

    @GetMapping("toPassword")
    private String toPassword() { return "/admin/system/password"; }

    @GetMapping("toWebSite")
    private String toWebSite() {
        return "/admin/system/website";
    }


    @GetMapping("toUserInfo")
    private ModelAndView toUserInfo(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/system/userInfo");
        return mv;
    }

    @ResponseBody
    @GetMapping("getUserInfo")
    private Result getUserInfo(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("loginUser");
        TbAdminUser userInfo = userService.getUserInfo(username);
        return new Result(0,"获取用户成功",userInfo);
    }

    @GetMapping("getIndexShow")
    @ResponseBody
    private Result getIndexShow() {
        Map<String, Object> map = new HashMap<>();
        map.put("num",1000);
        return new Result(0,"成功",map);
    }

    @ResponseBody
    @PostMapping("saveSite")
    private Result webSite(@RequestBody Map<String,Object> map){
        log.info("修改网站设置的入参为======================[{}]",map);
        try{
            List<TbBlogConfig> configs = adminService.saveWebSite(map);
            return new Result(0,"成功");
        }catch (Exception e){
            log.error("==============修改网站设置失败===========",e);
            return new Result(500,"失败");

        }
    }

    @ResponseBody
    @GetMapping("getWebSite")
    private Result getWebSite(){
        log.info("=======================开始查询所有网站设置===================");
        try{
            Map<String, String> webSite = adminService.getWebSite();
            return new Result(0,"成功",webSite);
        }catch (Exception e){
            log.error("======================查询网站设置异常=====================",e);
            return new Result(500,"查询失败");
        }
    }

    /**
     * 初始化导航页
     * @param request
     * @param response
     * @return
     */
    @GetMapping("home")
    private ModelAndView toHomePage2(HttpServletRequest request,
                                     HttpServletResponse response) {
        Integer viewsCount = articleService.getArticleViewsCount();
        Integer articleCount = articleService.getArticleCount();
        List<BlogLogVO> log = logService.getLatestLog();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/home/home");
        mv.addObject("viewsCount",viewsCount);
        mv.addObject("articleCount",articleCount);
        mv.addObject("logs",log);
        return mv;
    }
}
