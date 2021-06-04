package com.blog.controller.admin;

import com.blog.commons.Result;
import com.blog.pojo.TbBlogTagEntity;
import com.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: springboot
 * @ClassName: TagController
 * @Author: jian
 * @Description:
 * @Date: 2021/6/3 9:54
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("toTagsList")
    private String toTagsList(){return "/admin/tags/tagsList";}
    @GetMapping("toTagsEdit")
    private String toTagsEdit(){return "/admin/tags/tagsEdit";}

    @GetMapping("tagsList")
    @ResponseBody
    private Result tagsListByPage(@RequestParam(value = "page",required = true) Integer page,
                            @RequestParam(value = "limit",required = true) Integer limit){
        Pageable pageable = new PageRequest(page-1,limit);
        Page<TbBlogTagEntity> tagPage = tagsService.findTagByPage(pageable);
        List<TbBlogTagEntity> content = tagPage.getContent();
        System.out.println(content);
        return new Result(0,"成功",content.size(),content);
    }

    @PostMapping("saveTags")
    @ResponseBody
    private Result saveTags(@RequestBody TbBlogTagEntity tagEntity){
        TbBlogTagEntity entity = tagsService.saveTags(tagEntity);
        return new Result(0,"成功",entity);
    }
    @GetMapping("getAllTags")
    @ResponseBody
    private Result getAllTags(){
        List<TbBlogTagEntity> allTags = tagsService.getAllTags();
        return new Result(0,"成功",allTags);
    }
}
