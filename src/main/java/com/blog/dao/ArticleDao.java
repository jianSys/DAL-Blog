package com.blog.dao;

import com.blog.pojo.TbBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ProjectName: springboot
 * @ClassName: ArticleDao
 * @Author: jian
 * @Description:
 * @Date: 2021/5/28 14:20
 * @Version: 1.0
 */
public interface ArticleDao extends JpaRepository<TbBlog, Integer> {
}
