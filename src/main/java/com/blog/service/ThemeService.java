package com.blog.service;

import com.blog.pojo.TbBlogTheme;

import java.util.List;

/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-07-03 18:25
 **/
public interface ThemeService {
    /**
     * 获取所有主题
     * @return
     */
    List<TbBlogTheme> getAllTheme();

    /**
     * 修改主题
     * @param id
     */
    void replaceTheme(Integer id);
}
