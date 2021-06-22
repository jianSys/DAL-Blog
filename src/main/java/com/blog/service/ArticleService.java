package com.blog.service;

import com.blog.pojo.TbBlogCategory;
import com.blog.pojo.TbBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springboot
 * @ClassName: ArticleService
 * @Author: jian
 * @Description: 文章操作类
 * @Date: 2021/5/28 10:39
 * @Version: 1.0
 */
public interface ArticleService {

    TbBlog findById(Integer id);

    Page<TbBlog> findByPage(Map<String, Object> map, Pageable pageable);

    Page<TbBlogCategory> findCategoryByPage(Pageable pageable);

    List<TbBlogCategory> findAllCategory();

    List<TbBlog> findAllBlog();

    TbBlog save(TbBlog tbBlogEntity);

    TbBlogCategory findCategoryById(Integer id);

    void delArticleById(Integer id);

    Integer getArticleCount();

    Map<String,Object> indexData();
}
