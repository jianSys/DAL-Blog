package com.blog.controller.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: springboot
 * @ClassName: BlogController
 * @Author: jian
 * @Description:
 * @Date: 2021/5/26 17:18
 * @Version: 1.0
 */
@Controller
public class BlogController {
    @GetMapping("/blog/index")
    private String toIndex(){
        return "/blog/index";
    }
}
