package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.dao.ArticleDao;
import com.blog.mapper.BlogMapper;
import com.blog.pojo.TbBlog;
import com.blog.service.PageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private BlogMapper blogMapper;

    @Override
    public void savePage(TbBlog tbBlog) {
        tbBlog.setBlogTop(0);
        tbBlog.setBlogStatus(1);
        tbBlog.setBlogTags("我是页面");
        tbBlog.setCreateTime(new Date());
        tbBlog.setBlogCategoryName("页面");
        tbBlog.setBlogCategoryId(0);
        blogMapper.insert(tbBlog);
    }

    @Override
    public List<TbBlog> getAllPage() {
        QueryWrapper<TbBlog> wrapper = new QueryWrapper<>();
        //wrapper.lambda().isNotNull(TbBlog::getBlogSubUrl);
        List<TbBlog> blogList = blogMapper.selectList(wrapper);
        Iterator<TbBlog> it = blogList.iterator();
        while (it.hasNext()){
            TbBlog blog = it.next();
            if (StringUtils.isBlank(blog.getBlogSubUrl())){
                it.remove();
            }
        }
        return blogList;
    }
}
