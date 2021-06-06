package com.blog.dao;

import com.blog.pojo.TbBlogTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: SpringBoot
 * @description:
 * @author: jian
 * @create: 2021-05-29 09:09
 **/
public interface BlogTagDao extends JpaRepository<TbBlogTagEntity, Integer> {
}
