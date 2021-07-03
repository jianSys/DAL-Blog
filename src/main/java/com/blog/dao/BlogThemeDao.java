package com.blog.dao;

import com.blog.pojo.TbBlogTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: dal-blog
 * @description: 主题
 * @author: jian
 * @create: 2021-07-03 18:39
 **/
public interface BlogThemeDao extends JpaRepository<TbBlogTheme,Integer> {
    @Query(value = "select * from tb_blog_theme t where t.status = 1",nativeQuery = true)
    TbBlogTheme findUpTheme();
}
