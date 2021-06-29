package com.blog.dao;

import com.blog.pojo.TbBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
    //加入：nativeQuery注解时，写原生sql，支持limit函数
    //不加入：nativeQuery注解时是JPQL。JPQL不支持limit函数
    @Query(nativeQuery = true,value = "select * from tb_blog tb where tb.blog_status = 1 or tb.blog_sub_url is null order by tb.blog_views desc limit 0,4")
    List<TbBlog> getHotBlog();

    TbBlog findByBlogSubUrl(String blogSubUrl);
}
