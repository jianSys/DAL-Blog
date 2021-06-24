package com.blog.service.impl;

import com.blog.dao.BlogCategoryDao;
import com.blog.dao.BlogDao;
import com.blog.dao.BlogTagDao;
import com.blog.pojo.TbBlogCategory;
import com.blog.pojo.TbBlog;
import com.blog.pojo.TbBlogTag;
import com.blog.service.ArticleService;
import org.hibernate.internal.util.beans.BeanInfoHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.Predicate;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: springboot
 * @ClassName: ArticleServiceImpl
 * @Author: jian
 * @Description:
 * @Date: 2021/5/28 10:40
 * @Version: 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private BlogDao articleDao;

    @Autowired
    private BlogTagDao blogTagDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogCategoryDao blogCategoryDao;


    @Override
    public TbBlog findById(Integer id) {
        TbBlog tbBlogEntity = articleDao.findById(id).get();
        return tbBlogEntity;
    }

    /**
     * 分页查询所有博客文章
     *
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public Page<TbBlog> findByPage(Map<String, Object> map, Pageable pageable) {

        //List<TbBlogEntity> all = articleDao.findAll();
        Specification<TbBlog> spec = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Integer id = (Integer) map.get("id");
            if (id != null) {
                list.add(criteriaBuilder.equal(root.get("blogId"), id));
            }
            String title = (String) map.get("title");
            if (StringUtils.isNotBlank(title)) {
                list.add(criteriaBuilder.like(root.get("blogTitle"), title));
            }
            String blogCategoryName = (String) map.get("blogCategoryName");
            if (StringUtils.isNotBlank(blogCategoryName)) {
                list.add(criteriaBuilder.like(root.get("blogCategoryName"), blogCategoryName));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };

        Page<TbBlog> page = blogDao.findAll(spec, pageable);

        if (page == null || page.getTotalElements() < 1) {
            return new PageImpl<>(new ArrayList<>(0), pageable, 0);
        }

        List<TbBlog> lists = page.getContent();

        if (lists != null && lists.size() > 0) {

            //List<TbBlogEntity> arr = TbBlogEntity.taskData2Vo(lists);
            return new PageImpl<>(lists, pageable, page.getTotalElements());
        }
        return new PageImpl<>(new ArrayList<>(0), pageable, 0);
    }

    /**
     * 分页查询文章分类
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<TbBlogCategory> findCategoryByPage(Pageable pageable) {
        Page<TbBlogCategory> all = blogCategoryDao.findAll(pageable);
        return all;
    }

    @Override
    public List<TbBlogCategory> findAllCategory() {
        return blogCategoryDao.findAll();
    }

    @Override
    public List<TbBlog> findAllBlog() {
        return articleDao.findAll();
    }

    @Override
    public TbBlog save(TbBlog tbBlogEntity) {
        Integer blogId = tbBlogEntity.getBlogId();
        if (null != blogId) {
            tbBlogEntity.setUpdateTime(new Date());
            //Optional<TbBlog> tbBlog = blogDao.findById(blogId);
            //TbBlog blog = tbBlog.get();
            //更新
            if (null != tbBlogEntity.getBlogCategoryId()){
                TbBlogCategory category = this.findCategoryById(tbBlogEntity.getBlogCategoryId());
                tbBlogEntity.setBlogCategoryName(category.getCategoryName());
            }
            if (StringUtils.isNotBlank(tbBlogEntity.getBlogTags())){
                String tagsNames = getTagsNames(tbBlogEntity.getBlogTags());
                tbBlogEntity.setBlogTags(tagsNames);
            }
            //BeanUtils.copyProperties(tbBlog,tbBlogEntity);
            TbBlog save = blogDao.save(tbBlogEntity);
            return save;
        } else {
            tbBlogEntity.setUpdateTime(new Date());
            tbBlogEntity.setCreateTime(new Date());
            tbBlogEntity.setBlogViews(0);
            tbBlogEntity.setIsDeleted(0);
            if (null != tbBlogEntity.getBlogCategoryId()){
                TbBlogCategory category = this.findCategoryById(tbBlogEntity.getBlogCategoryId());
                tbBlogEntity.setBlogCategoryName(category.getCategoryName());
            }
            if (StringUtils.isNotBlank(tbBlogEntity.getBlogTags())){
                String tagsNames = getTagsNames(tbBlogEntity.getBlogTags());
                tbBlogEntity.setBlogTags(tagsNames);
            }
            TbBlog save = blogDao.save(tbBlogEntity);
            return save;
        }
    }

    @Override
    public TbBlogCategory findCategoryById(Integer id) {
        return blogCategoryDao.getOne(id);
    }

    @Override
    public void delArticleById(Integer id) {
        blogDao.deleteById(id);
    }

    @Override
    public Integer getArticleCount() {

        Integer count = articleDao.getBlogCount();
        return count;
    }

    /**
     * 组装首页显示
     * @return
     */
    @Override
    public Map<String, Object> indexData() {
        Map<String, Object> map = new HashMap<>();
        //查询最热文章
        Sort sort = new Sort(Sort.Direction.DESC, "blogViews");
        List<TbBlog> list = articleDao.findAll(sort);
        List<TbBlog> hotBlog = list.stream()
                .limit(4)
                .collect(Collectors.toList());

        map.put("hotBlog",hotBlog);
        map.put("allBlog",list);
        return map;
    }

    /**
     * 查看所有观看人数
     * @return
     */
    @Override
    public Integer getArticleViewsCount() {
        Integer count = articleDao.getBlogViewsCount();
        return count;
    }


    public String getTagsNames(String tagsIds) {
        String[] split = tagsIds.split(",");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            TbBlogTag entity = blogTagDao.getOne(Integer.parseInt(split[i]));
            if (i == split.length - 1) {
                builder.append(entity.getTagName());
            } else {
                builder.append(entity.getTagName()).append(",");
            }
        }
        return builder.toString();
    }
}
