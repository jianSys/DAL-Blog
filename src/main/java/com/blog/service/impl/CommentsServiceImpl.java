package com.blog.service.impl;

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
}
