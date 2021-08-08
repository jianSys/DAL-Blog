package com.blog.service;

import com.blog.pojo.TbBlogComment;

/**
 * @program: dal-blog
 * @description: 评论
 * @author: jian
 * @create: 2021-08-08 13:47
 **/
public interface CommentsService {

    TbBlogComment save(TbBlogComment comment);
}
