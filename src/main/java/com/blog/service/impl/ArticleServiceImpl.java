package com.blog.service.impl;

import com.blog.commons.enums.LogEnum;
import com.blog.commons.utils.DateUtils;
import com.blog.dao.BlogCategoryDao;
import com.blog.dao.BlogDao;
import com.blog.dao.BlogLogDao;
import com.blog.dao.BlogTagDao;
import com.blog.pojo.TbBlogCategory;
import com.blog.pojo.TbBlog;
import com.blog.pojo.TbBlogLog;
import com.blog.pojo.TbBlogTag;
import com.blog.pojo.vo.BlogVO;
import com.blog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Autowired
    private BlogLogDao logDao;

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
        //分页带动态条件查询文章
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
                list.add(criteriaBuilder.like(root.get("blogCategoryName"), "%" + blogCategoryName + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };

        Page<TbBlog> page = blogDao.findAll(spec, pageable);

        if (page == null || page.getTotalElements() < 1) {
            return new PageImpl<>(new ArrayList<>(0), pageable, 0);
        }

        List<TbBlog> lists = page.getContent();

        if (lists != null && lists.size() > 0) {
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
    public TbBlog save(TbBlog tbBlogEntity) {
        Integer blogId = tbBlogEntity.getBlogId();
        if (null != blogId) {
            tbBlogEntity.setUpdateTime(new Date());
            //更新
            if (null != tbBlogEntity.getBlogCategoryId()) {
                TbBlogCategory category = this.findCategoryById(tbBlogEntity.getBlogCategoryId());
                tbBlogEntity.setBlogCategoryName(category.getCategoryName());
            }
            if (StringUtils.isNotBlank(tbBlogEntity.getBlogTags())) {
                String tagsNames = getTagsNames(tbBlogEntity.getBlogTags());
                tbBlogEntity.setBlogTags(tagsNames);
            }
            TbBlog update = blogDao.save(tbBlogEntity);
            //文章修改日志
            logDao.save(
                    TbBlogLog.builder()
                            .operation(LogEnum.ARTICLE_UPDATE_OPERATION.getOperation())
                            .createTime(new Date())
                            .remark(update.getBlogTitle())
                            .build()
            );
            return update;
        } else {
            tbBlogEntity.setUpdateTime(new Date());
            tbBlogEntity.setCreateTime(new Date());
            tbBlogEntity.setBlogViews(0);
            tbBlogEntity.setIsDeleted(0);
            if (null != tbBlogEntity.getBlogCategoryId()) {
                TbBlogCategory category = this.findCategoryById(tbBlogEntity.getBlogCategoryId());
                tbBlogEntity.setBlogCategoryName(category.getCategoryName());
            }
            if (StringUtils.isNotBlank(tbBlogEntity.getBlogTags())) {
                String tagsNames = getTagsNames(tbBlogEntity.getBlogTags());
                tbBlogEntity.setBlogTags(tagsNames);
            }
            TbBlog save = blogDao.save(tbBlogEntity);
            //添加操作日志
            logDao.save(
                    TbBlogLog.builder()
                            .operation(LogEnum.ARTICLE_ADD_OPERATION.getOperation())
                            .createTime(new Date())
                            .remark(save.getBlogTitle())
                            .build()
            );
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
     *
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

        map.put("hotBlog", hotBlog);
        map.put("allBlog", list);
        return map;
    }

    /**
     * 查看所有观看人数
     *
     * @return
     */
    @Override
    public Integer getArticleViewsCount() {
        Integer count = articleDao.getBlogViewsCount();
        return count;
    }

    /**
     * 根据标签id获取标签字符串
     * @param tagsIds
     * @return
     */
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

    /**
     * 添加观看人数
     * @param id
     */
    @Override
    public void updateBlogViews(Integer id) {
        TbBlog blog = articleDao.getOne(id);
        Integer blogViews = blog.getBlogViews();
        blog.setBlogViews(blogViews + 1);
        TbBlog tbBlog = articleDao.save(blog);
    }

    /**
     * 前端页面展示开始
     */

    /**
     * 获取最热文章
     *
     * @return
     */
    @Override
    public List<BlogVO> getHotBlog() {
        List<TbBlog> hotBlog = articleDao.getHotBlog();
        ArrayList<BlogVO> blogVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(hotBlog)){
            hotBlog.forEach(
                    blog->{
                        BlogVO blogVO = new BlogVO();
                        BeanUtils.copyProperties(blog,blogVO);
                        blogVOS.add(blogVO);
                    }
            );
        }

        return blogVOS;
    }

    /**
     * 获取首页显示所有文章
     *
     * @return
     */
    @Override
    public List<BlogVO> getAllBlog() {

        Specification<TbBlog> spec = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            //判断状态已发布
            list.add(criteriaBuilder.equal(root.get("blogStatus"), 1));
            //未删除
            list.add(criteriaBuilder.equal(root.get("isDeleted"), 0));
            //有地址的是页面,没有地址的是文章
            list.add(criteriaBuilder.isNull(root.get("blogSubUrl")));

            Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            criteriaQuery.where(predicate);
            //根据创建时间排序
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));
            return criteriaQuery.getRestriction();
        };
        List<TbBlog> blogs = articleDao.findAll(spec);
        ArrayList<BlogVO> blogVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(blogs)){
            blogs.forEach(
                    blog->{
                        BlogVO blogVO = new BlogVO();
                        BeanUtils.copyProperties(blog,blogVO);
                        if (null != blogVO.getCreateTime()){
                            try {
                                //处理页面显示时间
                                blogVO.setTime(DateUtils.parseDate2String(blogVO.getCreateTime(),"yyyy年MM月"));
                                blogVO.setDay(DateUtils.parseDate2String(blogVO.getCreateTime(),"dd"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        blogVOS.add(blogVO);

                    }
            );
        }
        return blogVOS;
    }

    @Override
    public BlogVO getPageByUrl(String url) {
        TbBlog blog = articleDao.findByBlogSubUrl(url);
        BlogVO blogVO = new BlogVO();
        BeanUtils.copyProperties(blog,blogVO);
        try {
            String time = DateUtils.parseDate2String(blogVO.getCreateTime(), "yyyy-MM-dd");
            blogVO.setTime(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogVO;
    }

    @Override
    public BlogVO getBlogById(Integer id) {
        TbBlog blog = articleDao.getOne(id);
        BlogVO blogVO = new BlogVO();
        BeanUtils.copyProperties(blog,blogVO);
        String time = null;
        try {
            //处理时间
            time = DateUtils.parseDate2String(blogVO.getCreateTime(),"yyyy-MM-dd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        blogVO.setTime(time);
        return blogVO;
    }

    /**
     * 前端页面展示结束
     */
}
