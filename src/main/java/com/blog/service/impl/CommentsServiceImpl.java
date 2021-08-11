package com.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.dao.BlogCommentsDao;
import com.blog.mapper.BlogCommentMapper;
import com.blog.pojo.TbBlogComment;
import com.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: dal-blog
 * @description: 评论
 * @author: jian
 * @create: 2021-08-08 13:48
 **/
@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private BlogCommentMapper commentsMapper;


    @Override
    public TbBlogComment save(TbBlogComment comment) {
        /*comment.setCommentCreateTime(new Date());
        comment.setCommentStatus(0);
        comment.setIsDeleted(0);
        return commentsDao.save(comment);*/
        return null;
    }

    @Override
    public PageResult<TbBlogComment> page(PageDomain domain, TbBlogComment comment) {
        IPage<TbBlogComment> page = new Page(domain.getPage(),domain.getLimit());
        QueryWrapper<TbBlogComment> wrapper = new QueryWrapper<>();
        IPage<TbBlogComment> iPage = commentsMapper.selectPage(page, wrapper);
        return new PageResult<>(iPage.getRecords(),iPage.getSize(),iPage.getTotal());
    }
}
