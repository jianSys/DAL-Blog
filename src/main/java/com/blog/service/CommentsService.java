package com.blog.service;


import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.pojo.TbBlogComment;

import java.util.List;

/**
 * @program: dal-blog
 * @description: 评论
 * @author: jian
 * @create: 2021-08-08 13:47
 **/
public interface CommentsService {

    TbBlogComment save(TbBlogComment comment);

    PageResult<TbBlogComment> page(PageDomain domain, TbBlogComment comment);
}
