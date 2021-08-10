package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.TbBlog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: dal-blog
 * @description: 博客mapper
 * @author: jian
 * @create: 2021-08-09 21:32
 **/
public interface BlogMapper extends BaseMapper<TbBlog> {

    @Select("select count(t.blog_id) from tb_blog t")
    Integer getBlogCount();
    @Select("select sum(t.blog_views) from tb_blog t")
    Integer getBlogViewsCount();

    @Select("select * from tb_blog")
    List<TbBlog> findAll();

}
