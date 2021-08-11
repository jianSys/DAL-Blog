package com.blog.controller.admin;

import cn.hutool.json.JSONUtil;
import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
@RestController
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX + "/tags")
public class TagsController extends BaseController {

    public static String MODULE_PATH = "/admin/tags/";

    @Resource
    private TagsService tagsService;

    @GetMapping("toTagsList")
    private ModelAndView toTagsList() {
        return jumpPage(MODULE_PATH + "tagsList");
    }

    @GetMapping("toTagsEdit")
    private ModelAndView toTagsEdit() {
        return jumpPage(MODULE_PATH + "tagsEdit");
    }

    @GetMapping("tagsList")
    @ResponseBody
    private Result tagsListByPage(PageDomain domain) {
        log.info("查询标签列表的入参为=================[{}]", domain);
        try {
            PageResult<TbBlogTag> tagPage = tagsService.findTagByPage(domain);
            Long total = tagPage.getTotal();
            List<TbBlogTag> content = tagPage.getData();
            System.out.println(content);
            return new Result(0, "成功", total.intValue(), content);
        } catch (Exception e) {
            log.error("查询标签列表异常", e);
            return Result.error("查询标签列表失败");
        }
    }

    @PostMapping("saveTags")
    @ResponseBody
    private Result saveTags(@RequestBody TbBlogTag tagEntity) {
        log.info("=============保存标签的入参为==========[{}]", JSONUtil.parse(tagEntity));
        try {
            tagsService.saveTags(tagEntity);
            return Result.ok("成功");
        } catch (Exception e) {
            log.error("=============保存标签异常===========", e);
            return Result.error("失败");
        }

    }

    @GetMapping("getAllTags")
    @ResponseBody
    private Result getAllTags() {
        log.info("===============查询所有标签===============");
        try {
            List<TbBlogTag> allTags = tagsService.getAllTags();
            return new Result(0, "成功", allTags);
        } catch (Exception e) {
            log.error("==============查询便签列表失败================", e);
            return Result.error(500, "失败");
        }

    }
}
