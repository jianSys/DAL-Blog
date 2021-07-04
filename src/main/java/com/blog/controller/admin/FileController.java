package com.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: dal-blog
 * @description: 附属文件地址
 * @author: jian
 * @create: 2021-07-04 22:31
 **/
@Controller
@RequestMapping("/admin/file")
public class FileController {

    @GetMapping("toFileList")
    private ModelAndView toFileList(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/file/fileList");
        return mv;
    }
}
