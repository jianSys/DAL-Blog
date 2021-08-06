package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.web.domain.response.Result;
import com.blog.commons.constant.ControllerConstant;
import com.blog.pojo.TbBlogTag;
import com.blog.service.TagsService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@Controller
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX+"/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("toTagsList")
    private String toTagsList() {
        return "/admin/tags/tagsList";
    }

    @GetMapping("toTagsEdit")
    private String toTagsEdit() {
        return "/admin/tags/tagsEdit";
    }

    @GetMapping("tagsList")
    @ResponseBody
    private Result tagsListByPage(@RequestParam(value = "page", required = true) Integer page,
                                  @RequestParam(value = "limit", required = true) Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        log.info("查询标签列表的入参为[{},{}]",page,limit);
        try {
            Page<TbBlogTag> tagPage = tagsService.findTagByPage(pageable);
            Long total = tagPage.getTotalElements();
            List<TbBlogTag> content = tagPage.getContent();
            System.out.println(content);
            return new Result(0, "成功", total.intValue(), content);
        }catch (Exception e){
            log.error("查询标签列表异常",e);
            return new Result(500, "失败");
        }
    }

    @PostMapping("saveTags")
    @ResponseBody
    private Result saveTags(@RequestBody TbBlogTag tagEntity) {
        log.info("=============保存标签的入参为==========[{}]", JSONUtil.parse(tagEntity));
        try {
            TbBlogTag entity = tagsService.saveTags(tagEntity);
            return new Result(0, "成功", entity);
        }catch (Exception e){
            log.error("=============保存标签异常===========",e);
            return new Result(500, "失败");
        }

    }

    @GetMapping("getAllTags")
    @ResponseBody
    private Result getAllTags() {
        log.info("===============查询所有标签===============");
        try {
            List<TbBlogTag> allTags = tagsService.getAllTags();
            return new Result(0, "成功", allTags);
        }catch (Exception e){
            log.error("==============查询便签列表失败================",e);
            return new Result(500, "失败");
        }

    }
}
