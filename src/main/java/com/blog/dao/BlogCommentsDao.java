package com.blog.dao;

import com.blog.pojo.TbBlogCategory;
import com.blog.pojo.TbBlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: dal-blog
 * @description: 评论
 * @author: jian
 * @create: 2021-08-08 13:50
 **/
public interface BlogCommentsDao extends JpaRepository<TbBlogComment, Integer> {
}
