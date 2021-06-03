package com.blog.service;

import com.blog.pojo.TbBlogCategoryEntity;
import com.blog.pojo.TbBlogEntity;
import com.blog.pojo.TbBlogTagEntity;
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

    TbBlogEntity findById(Integer id);

    Page<TbBlogEntity> findByPage(Map<String, Object> map, Pageable pageable);

    Page<TbBlogCategoryEntity> findCategoryByPage(Pageable pageable);

    List<TbBlogCategoryEntity> findAllCategory();

    TbBlogEntity save(TbBlogEntity tbBlogEntity);

    TbBlogCategoryEntity findCategoryById(Integer id);

    void delArticleById(Integer id);
}
