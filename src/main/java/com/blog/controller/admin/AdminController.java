package com.blog.controller.admin;

import com.blog.commons.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

    @GetMapping({"", "/", "/index", "/index.ftl"})
    private String index() {
        return "/admin/index";
    }

    @GetMapping("toTool")
    private String toTool() {
        return "/admin/system/tool";
    }
    @GetMapping("getIndexShow")
    @ResponseBody
    private Result getIndexShow() {
        Map<String, Object> map = new HashMap<>();
        map.put("num",1000);
        return new Result(0,"成功",map);
    }
}
