package com.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @ProjectName: dal-blog
 * @ClassName: AdminController
 * @Author: jian
 * @Description:
 * @Date: 2021/6/3 16:59
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("index")
    private String index(){
        return "/admin/index";
    }
}
