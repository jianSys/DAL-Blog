package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.dao.BlogTagDao;
import com.blog.mapper.BlogTagMapper;
import com.blog.pojo.TbBlogTag;
import com.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-06-03 22:04
 **/
@Service
public class TagsServiceImpl implements TagsService {

    @Resource
    private BlogTagMapper tagMapper;

    /**
     * 分页查询所有博客标签
     *
     * @param domain
     * @return
     */
    @Override
    public PageResult<TbBlogTag> findTagByPage(PageDomain domain) {
        IPage<TbBlogTag> page = new Page(domain.getPage(),domain.getLimit());
        QueryWrapper<TbBlogTag> wrapper = new QueryWrapper<>();
        IPage<TbBlogTag> iPage = tagMapper.selectPage(page, wrapper);
        return new PageResult<>(iPage.getRecords(),iPage.getSize(),iPage.getTotal());
    }

    /**
     * 添加标签数据
     *
     * @param tbBlogTag
     */
    @Override
    public void saveTags(TbBlogTag tbBlogTag) {
        Integer tagId = tbBlogTag.getTagId();
        if (null != tagId) {
            tagMapper.updateById(tbBlogTag);
        } else {
            tbBlogTag.setCreateTime(new Date());
            tbBlogTag.setIsDeleted(0);
            tagMapper.insert(tbBlogTag);
        }

    }

    @Override
    public List<TbBlogTag> getAllTags() {
        QueryWrapper<TbBlogTag> wrapper = new QueryWrapper<>();
        List<TbBlogTag> tagList = tagMapper.selectList(wrapper);
        return tagList;
    }
}
