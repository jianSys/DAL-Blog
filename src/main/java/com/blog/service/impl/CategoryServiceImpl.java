package com.blog.service.impl;

import com.blog.dao.BlogCategoryDao;
import com.blog.pojo.TbBlogCategoryEntity;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Autowired
    private BlogCategoryDao categoryDao;

    /**
     * find all category
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<TbBlogCategoryEntity> findCategoryByPage(Pageable pageable) {
        Page<TbBlogCategoryEntity> all = categoryDao.findAll(pageable);
        return all;
    }
}
