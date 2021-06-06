package com.blog.service.impl;

import com.blog.dao.BlogCategoryDao;
import com.blog.dao.BlogDao;
import com.blog.dao.BlogTagDao;
import com.blog.pojo.TbBlogCategoryEntity;
import com.blog.pojo.TbBlogEntity;
import com.blog.pojo.TbBlogTagEntity;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;

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
    public TbBlogEntity findById(Integer id) {
        TbBlogEntity tbBlogEntity = articleDao.findById(id).get();
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
    public Page<TbBlogEntity> findByPage(Map<String, Object> map, Pageable pageable) {

        //List<TbBlogEntity> all = articleDao.findAll();
        Specification<TbBlogEntity> spec = (root, criteriaQuery, criteriaBuilder) -> {
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

        Page<TbBlogEntity> page = blogDao.findAll(spec, pageable);

        if (page == null || page.getTotalElements() < 1) {
            return new PageImpl<>(new ArrayList<>(0), pageable, 0);
        }

        List<TbBlogEntity> lists = page.getContent();

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
    public Page<TbBlogCategoryEntity> findCategoryByPage(Pageable pageable) {
        Page<TbBlogCategoryEntity> all = blogCategoryDao.findAll(pageable);
        return all;
    }

    @Override
    public List<TbBlogCategoryEntity> findAllCategory() {
        return blogCategoryDao.findAll();
    }

    @Override
    public TbBlogEntity save(TbBlogEntity tbBlogEntity) {
        Integer blogId = tbBlogEntity.getBlogId();
        if (null != blogId) {
            tbBlogEntity.setUpdateTime(new Date());
            tbBlogEntity.setIsDeleted(0);
            //更新
            return null;
        } else {
            tbBlogEntity.setUpdateTime(new Date());
            tbBlogEntity.setCreateTime(new Date());
            tbBlogEntity.setBlogViews(0);
            tbBlogEntity.setIsDeleted(0);
            TbBlogCategoryEntity category = this.findCategoryById(tbBlogEntity.getBlogCategoryId());
            tbBlogEntity.setBlogCategoryName(category.getCategoryName());
            String tagsNames = getTagsNames(tbBlogEntity.getBlogTags());
            tbBlogEntity.setBlogTags(tagsNames);
            TbBlogEntity save = blogDao.save(tbBlogEntity);
            return save;
        }
    }

    @Override
    public TbBlogCategoryEntity findCategoryById(Integer id) {
        return blogCategoryDao.getOne(id);
    }

    @Override
    public void delArticleById(Integer id) {
        blogDao.deleteById(id);
    }


    public String getTagsNames(String tagsIds) {
        String[] split = tagsIds.split(",");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            TbBlogTagEntity entity = blogTagDao.getOne(Integer.parseInt(split[i]));
            if (i == split.length - 1) {
                builder.append(entity.getTagName());
            } else {
                builder.append(entity.getTagName()).append(",");
            }
        }
        return builder.toString();
    }
}
