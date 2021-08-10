package com.blog.service.impl;

import com.blog.dao.BlogCategoryDao;
import com.blog.mapper.BlogCategoryMapper;
import com.blog.pojo.TbBlogCategory;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
     * @param pageable
     * @return
     */
    @Override
    public Page<TbBlogCategory> findCategoryByPage(Pageable pageable) {
        //Page<TbBlogCategory> all = categoryDao.findAll(pageable);
        return null;
    }
}
