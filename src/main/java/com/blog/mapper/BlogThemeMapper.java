package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.TbBlogTheme;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogThemeMapper
 * @Author: jian
 * @Description: 博客主题
 * @Date: 2021/8/10 10:35
 * @Version: 1.0
 */
public interface BlogThemeMapper extends BaseMapper<TbBlogTheme> {
    @Select("select * from tb_blog_theme")
    List<TbBlogTheme> findAll();
}
