package com.blog.controller.admin;

import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.TbBlogFile;
import com.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: dal-blog
 * @description: 附属文件地址
 * @author: jian
 * @create: 2021-07-04 22:31
 **/
@Controller
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX+"/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("toFileList")
    private ModelAndView toFileList(){
        return this.getFileListByPage(1);
    }

    @GetMapping("toFileDetails/{id}")
    private ModelAndView toFileDetails(@PathVariable("id")Integer id){
        TbBlogFile file = fileService.getFileById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/file/fileDetails");
        mv.addObject("file",file);
        return mv;
    }


    @ResponseBody
    @GetMapping("page/{pageNum}")
    private ModelAndView getFileListByPage(@PathVariable("pageNum") int pageNum){

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable =PageRequest.of(pageNum - 1, Integer.MAX_VALUE,sort);
        List<TbBlogFile> fileList = fileService.getFileListByPage(pageable);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/file/fileList");
        mv.addObject("total",fileList.size());
        mv.addObject("fileList",fileList);
        return mv;
    }
}
