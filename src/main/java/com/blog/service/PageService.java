package com.blog.service;

import com.blog.pojo.TbBlog;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: PageService
 * @Author: jian
 * @Description: 页面操作
 * @Date: 2021/6/29 16:17
 * @Version: 1.0
 */
public interface PageService {
    /**
     * 添加页面
     * @param tbBlog
     * @return
     */
    void savePage(TbBlog tbBlog);

    /**
     * 查询所有页面
     * @return
     */
    List<TbBlog> getAllPage();
}
