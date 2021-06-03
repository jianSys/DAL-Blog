package com.blog.dao;

import com.blog.pojo.TbBlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ProjectName: springboot
 * @ClassName: ArticleDao
 * @Author: jian
 * @Description:
 * @Date: 2021/5/28 14:20
 * @Version: 1.0
 */
public interface ArticleDao extends JpaRepository<TbBlogEntity,Integer> {
}
