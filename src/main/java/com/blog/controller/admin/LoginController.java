package com.blog.controller.admin;

import com.blog.commons.Result;
import com.blog.pojo.LoginUser;
import com.blog.pojo.TbAdminUser;
import com.blog.pojo.User;
import com.blog.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

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

    @Autowired
    private UserService userService;
    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping("login")
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
    @PostMapping(value = "login")
    private Result login(@RequestBody User user, HttpSession session) {
        TbAdminUser login = userService.login(user.getUsername(), user.getPassword());
        if (null == login){
            return new Result(500, "登录失败");
        }
        System.out.println(user);
        session.setAttribute("loginUser",login.getLoginUserName());
        LoginUser loginUser = new LoginUser();
        loginUser.setId(login.getAdminUserId());
        loginUser.setUsername(login.getLoginUserName());
        loginUser.setPhone(login.getEmail());
        loginUser.setAccessToken("是jvgvggjhfg");
        return new Result(0, "成功", 200, loginUser);
    }

    @ResponseBody
    @PostMapping("validation")
    private Result validation(@RequestBody Map<String,String>  oldPassword) {
        Boolean validation = userService.validation(oldPassword.get("oldPassword"));
        if (validation){
            return new Result(0,"成功");
        }else {
            return new Result(500,"失败");
        }
    }

    @ResponseBody
    @PostMapping("updatePassword")
    private Result updatePassword(@RequestBody Map<String,String>  map, HttpSession session) {
        System.out.println(map);
        TbAdminUser adminUser = userService.updatePassword(map.get("repassword"));
        session.removeAttribute("loginUser");
        return new Result(0,"成功",adminUser);
    }


    @GetMapping("logout")
    private String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/admin/login";
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
