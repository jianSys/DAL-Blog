package com.blog.dao;

import com.blog.pojo.TbBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @ProjectName: springboot
 * @ClassName: ArticleDao
 * @Author: jian
 * @Description:
 * @Date: 2021/5/28 14:20
 * @Version: 1.0
 */
public interface BlogDao extends JpaRepository<TbBlog, Integer>, JpaSpecificationExecutor<TbBlog> {

    @Query(value = "select sum (blogViews) from TbBlog ")
    Integer getBlogViewsCount();
    @Query(value = "select count (blogId)from TbBlog ")
    Integer getBlogCount();
}
