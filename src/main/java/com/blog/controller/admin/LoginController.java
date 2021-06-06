package com.blog.controller.admin;

import com.blog.commons.Result;
import com.blog.pojo.LoginUser;
import com.blog.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringBoot
 * @description:
 * @author: jian
 * @create: 2021-05-23 03:15
 **/
@Log4j2
@Controller
@RequestMapping("/admin")
public class LoginController {
    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping("/login")
    private String login() {
        return "/admin/login";
    }

    /**
     * 登录类
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login")
    private Result login(@RequestBody User user) {
        System.out.println(user);
        LoginUser loginUser = new LoginUser();
        loginUser.setId("fhgjgfghfggfj");
        loginUser.setAccessToken("是jvgvggjhfg");
        return new Result(0, "成功", 200, loginUser);
    }

    @GetMapping("home/console")
    private String toConsole() {
        return "/admin/home/console";
    }

    @GetMapping("home/homepage2")
    private String toHomePage2() {
        return "/admin/home/homepage2";
    }

    @GetMapping("app/content/comment")
    private String toComment() {
        return "/admin/app/content/comment";
    }

    @GetMapping("app/content/tags")
    private String toArticleTags() {
        return "/admin/app/content/tags";
    }

    @GetMapping("app/content/tagsform")
    private String toArticleTagsform() {
        return "/admin/app/content/tagsform";
    }

    @GetMapping("app/content/listform")
    private String toArticleListform() {
        return "/admin/app/content/listform";
    }


}
