package com.blog.service;

import com.blog.pojo.TbBlogCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ProjectName: springboot
 * @ClassName: CategoryService
 * @Author: jian
 * @Description: category operation
 * @Date: 2021/6/3 10:10
 * @Version: 1.0
 */
public interface CategoryService {
    Page<TbBlogCategoryEntity> findCategoryByPage(Pageable pageable);
}
