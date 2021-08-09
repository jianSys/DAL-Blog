package com.blog.service;

import com.blog.pojo.TbBlogCategory;
import com.blog.pojo.TbBlog;
import com.blog.pojo.vo.BlogVO;
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
    /**
     * 根据id查找博客
     * @param id
     * @return
     */
    TbBlog findById(Integer id);

    Page<TbBlog> findByPage(Map<String, Object> map, Pageable pageable);

    Page<TbBlogCategory> findCategoryByPage(Pageable pageable);

    List<TbBlogCategory> findAllCategory();


    TbBlog save(TbBlog tbBlogEntity);

    TbBlogCategory findCategoryById(Integer id);

    void delArticleById(Integer id);

    Integer getArticleCount();

    Map<String,Object> indexData();

    Integer getArticleViewsCount();

    void updateBlogViews(Integer id);

    //前端页面显示开始
    /**
     * 获取最热的文章
     * @return
     */
    List<BlogVO> getHotBlog();
    /**
     * 首页显示所有文章
     * @return
     */
    List<BlogVO> getAllBlog();
    /**
     * 根据url查找文章
     */
    BlogVO getPageByUrl(String url);
    /**
     * 根据id查询文章
     */
    BlogVO getBlogById(Integer id);

    /**
     * 获取归档首页
     * @return
     */
    List<BlogVO> getArchiveBlog();

    Page<BlogVO> getPageBlog(Integer pageNum);
    //前端页面显示结束
}
