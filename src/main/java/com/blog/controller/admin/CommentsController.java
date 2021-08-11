package com.blog.controller.admin;

import com.blog.commons.constant.ControllerConstant;
import com.blog.commons.utils.IpUtil;
import com.blog.commons.web.base.BaseController;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.commons.web.domain.response.Result;
import com.blog.pojo.TbBlogComment;
import com.blog.service.CommentsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;

/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-06-05 17:59
 **/
@Log4j2
@RestController
@RequestMapping(ControllerConstant.API_ADMIN_PREFIX + "/comments")
public class CommentsController extends BaseController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("toCommentsList")
    private ModelAndView toCommentsList() {
        return jumpPage("/admin/comments/commentsList");
    }

    @PostMapping("save")
    private Result save(HttpServletRequest request, @RequestBody TbBlogComment comment){
        String ipAddr = IpUtil.getIpAddr(request);
        comment.setCommentatorIp(ipAddr);
        commentsService.save(comment);
        return Result.ok("评论成功");
    }

    /**
     * 获取评价分页信息
     * @param domain
     * @param comment
     * @return
     */
    @GetMapping("getPage")
    private Result page(PageDomain domain,TbBlogComment comment){
        PageResult<TbBlogComment> page = commentsService.page(domain, comment);
        return new Result(0,"查询列表成功",page.getTotal().intValue(),page.getData());
    }
}
