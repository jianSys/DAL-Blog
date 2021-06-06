package com.blog.dao;

import com.blog.pojo.TbBlogCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: SpringBoot
 * @description:
 * @author: jian
 * @create: 2021-05-29 10:12
 **/
public interface BlogCategoryDao extends JpaRepository<TbBlogCategoryEntity, Integer> {
}
