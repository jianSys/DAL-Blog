package com.blog.service;

import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.pojo.TbBlogCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ProjectName: springboot
 * @ClassName: CategoryService
 * @Author: jian
 * @Description: category operation
 * @Date: 2021/6/3 10:10
 * @Version: 1.0
 */
public interface CategoryService {
    PageResult<TbBlogCategory> findCategoryByPage(PageDomain domain);
    List<TbBlogCategory> findAllCategory();
}
