package com.blog.controller.admin;

import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.LoginUser;
import com.blog.pojo.TbAdminUser;
import com.blog.pojo.User;
import com.blog.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX)
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
    private Result login(@RequestBody User user, HttpSession session, HttpServletRequest request) {
        if (StringUtils.isBlank(user.getVercode())) {
            return Result.error("验证码不能为空");
        }
        if (!CaptchaUtil.ver(user.getVercode(), request)) {
            return Result.error("验证码错误");
        }
        TbAdminUser login = userService.login(user.getUsername(), user.getPassword());
        if (null == login) {
            return Result.error("登录失败");
        }
        System.out.println(user);
        session.setAttribute("loginUser", login.getLoginUserName());
        LoginUser loginUser = new LoginUser();
        loginUser.setId(login.getAdminUserId());
        loginUser.setUsername(login.getLoginUserName());
        loginUser.setPhone(login.getEmail());
        //TODO生成token(待做....)
        loginUser.setAccessToken("iuhfiuadhfjahfakjdfhakjfhakjhfakjh");
        return Result.ok("登录成功", loginUser);
    }

    @ResponseBody
    @PostMapping("validation")
    private Result validation(@RequestBody Map<String, String> oldPassword, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("loginUser");
        String password = oldPassword.get("oldPassword");
        if (StringUtils.isBlank(password)) {
            return new Result(400, "密码不能为空");
        }
        Boolean validation = userService.validation(username, password);
        if (validation) {
            return new Result(0, "成功");
        } else {
            return new Result(500, "失败");
        }
    }

    /**
     * 修改登录用户密码
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("updatePassword")
    private Result updatePassword(@RequestBody Map<String, String> map, HttpSession session) {
        System.out.println(map);
        try{
            userService.updatePassword(map.get("repassword"));
            session.removeAttribute("loginUser");
            return Result.ok("密码修改成功");
        }catch (Exception e){
            log.error("=================修改登录密码异常==============",e);
            return Result.error("修改密码失败");
        }
    }


    @GetMapping("logout")
    @ResponseBody
    private Result logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return new Result(0, "退出成功");
    }


    @GetMapping("home/console")
    private String toConsole() {
        return "/admin/home/console";
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
