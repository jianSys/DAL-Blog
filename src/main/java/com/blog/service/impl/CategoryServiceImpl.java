package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.dao.BlogCategoryDao;
import com.blog.mapper.BlogCategoryMapper;
import com.blog.pojo.TbBlogCategory;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: springboot
 * @ClassName: CategoryServiceImpl
 * @Author: jian
 * @Description:
 * @Date: 2021/6/3 10:10
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private BlogCategoryMapper categoryMapper;

    /**
     * find all category
     *
     * @param domain
     * @return
     */
    @Override
    public PageResult<TbBlogCategory> findCategoryByPage(PageDomain domain) {
        IPage<TbBlogCategory> page = new Page(domain.getPage(),domain.getLimit());
        QueryWrapper<TbBlogCategory> wrapper = new QueryWrapper<>();
        IPage<TbBlogCategory> iPage = categoryMapper.selectPage(page, wrapper);
        return new PageResult<>(iPage.getRecords(),iPage.getSize(),iPage.getTotal());
    }

    @Override
    public List<TbBlogCategory> findAllCategory() {
        QueryWrapper<TbBlogCategory> wrapper = new QueryWrapper<>();
        List<TbBlogCategory> categoryList = categoryMapper.selectList(wrapper);
        return categoryList;
    }
}
