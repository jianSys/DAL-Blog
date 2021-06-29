package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.pojo.TbBlog;
import com.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ProjectName: dal-blog
 * @ClassName: PageServiceImpl
 * @Author: jian
 * @Description:
 * @Date: 2021/6/29 16:19
 * @Version: 1.0
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public TbBlog savePage(TbBlog tbBlog) {
        System.out.println(tbBlog);
        if (null==tbBlog){
            return null;
        }
        tbBlog.setBlogTop(0);
        tbBlog.setBlogStatus(1);
        tbBlog.setBlogTags("我是页面");
        tbBlog.setCreateTime(new Date());
        tbBlog.setBlogCategoryName("页面");
        tbBlog.setBlogCategoryId(0);
        return articleDao.save(tbBlog);
    }
}
