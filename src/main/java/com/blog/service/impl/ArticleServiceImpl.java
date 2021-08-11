package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.commons.enums.LogEnum;
import com.blog.commons.utils.DateUtils;
import com.blog.commons.web.domain.request.PageDomain;
import com.blog.commons.web.domain.response.PageResult;
import com.blog.mapper.*;
import com.blog.pojo.*;
import com.blog.pojo.vo.BlogVO;
import com.blog.service.ArticleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.List;

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

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    @Resource
    private BlogLogMapper blogLogMapper;

    @Resource
    private BlogTagRelationMapper tagRelationMapper;

    @Override
    public TbBlog findById(Integer id) {
        TbBlog blog = blogMapper.selectById(id);
        return blog;
    }

    /**
     * 分页查询所有博客文章
     *
     * @param domain
     * @param blog
     * @return
     */
    @Override
    public PageResult<TbBlog> findByPage(PageDomain domain, TbBlog blog) {
        //创建分页
        IPage<TbBlog> page = new Page(domain.getPage(),domain.getLimit());
        //条件查询
        QueryWrapper<TbBlog> wrapper = new QueryWrapper<>();
        Integer blogId = blog.getBlogId();
        if (null != blogId){
            wrapper.lambda().eq(TbBlog::getBlogId,blogId);
        }
        String blogTitle = blog.getBlogTitle();
        if (StringUtils.isNotBlank(blogTitle)){
            wrapper.lambda().eq(TbBlog::getBlogTitle,blogTitle);
        }
        String blogCategoryName = blog.getBlogCategoryName();
        if (StringUtils.isNotBlank(blogCategoryName)){
            wrapper.lambda().like(TbBlog::getBlogCategoryName,blogCategoryName);
        }
        IPage<TbBlog> iPage = blogMapper.selectPage(page, wrapper);
        return new PageResult<>(iPage.getRecords(),iPage.getSize(),iPage.getTotal());
        /*//分页带动态条件查询文章
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
        return new PageImpl<>(new ArrayList<>(0), pageable, 0);*/

    }

    /**
     * 分页查询文章分类
     *
     * @param pageable
     * @return
     */
    @Override
    public PageResult findCategoryByPage(Pageable pageable) {
        //Page<TbBlogCategory> all = blogCategoryDao.findAll(pageable);
        return null;
    }

    @Override
    public List<TbBlogCategory> findAllCategory() {
        return null;
    }

    @Override
    public TbBlog save(TbBlog blog) {
        Integer blogId = blog.getBlogId();
        String blogTags = blog.getBlogTags();
        if (null != blogId) {
            blog.setUpdateTime(new Date());
            //更新
            if (null != blog.getBlogCategoryId()) {
                TbBlogCategory category = this.findCategoryById(blog.getBlogCategoryId());
                blog.setBlogCategoryName(category.getCategoryName());
            }
            if (StringUtils.isNotBlank(blogTags)) {
                saveTagRelation(blogTags,blogId);
                String tagsNames = getTagsNames(blogTags);
                blog.setBlogTags(tagsNames);
            }
            blogMapper.updateById(blog);
            //文章修改日志
            blogLogMapper.insert(
                    TbBlogLog.builder()
                            .operation(LogEnum.ARTICLE_UPDATE_OPERATION.getOperation())
                            .createTime(new Date())
                            .remark(blog.getBlogTitle())
                            .build()
            );
            return null;
        } else {
            blog.setUpdateTime(new Date());
            blog.setCreateTime(new Date());
            blog.setBlogViews(0);
            blog.setIsDeleted(0);
            if (null != blog.getBlogCategoryId()) {
                TbBlogCategory category = this.findCategoryById(blog.getBlogCategoryId());
                blog.setBlogCategoryName(category.getCategoryName());
            }
            if (StringUtils.isNotBlank(blogTags)) {
                String tagsNames = getTagsNames(blogTags);
                blog.setBlogTags(tagsNames);
            }
            blogMapper.insert(blog);
            //添加操作日志
            blogLogMapper.insert(
                    TbBlogLog.builder()
                            .operation(LogEnum.ARTICLE_ADD_OPERATION.getOperation())
                            .createTime(new Date())
                            .remark(blog.getBlogTitle())
                            .build()
            );
            return null;
        }
    }

    @Override
    public TbBlogCategory findCategoryById(Integer id) {
        return null;
    }

    @Override
    public void delArticleById(Integer id) {
        blogMapper.deleteById(id);
      /*  TbBlog blog = blogDao.getOne(id);
        blog.setIsDeleted(1);
        blog.setBlogStatus(0);
        blogDao.save(blog);*/
    }

    /**
     * 查看所有文章数
     * @return
     */
    @Override
    public Integer getArticleCount() {
        Integer count = blogMapper.getBlogCount();
        return count;
    }

    /**
     * 组装首页显示
     *
     * @return
     */
    @Override
    public Map<String, Object> indexData() {
        /*Map<String, Object> map = new HashMap<>();
        //查询最热文章
        Sort sort = new Sort(Sort.Direction.DESC, "blogViews");
        List<TbBlog> list = articleDao.findAll(sort);
        List<TbBlog> hotBlog = list.stream()
                .limit(4)
                .collect(Collectors.toList());

        map.put("hotBlog", hotBlog);
        map.put("allBlog", list);
        return map;*/
        return null;
    }

    /**
     * 查看所有观看人数
     *
     * @return
     */
    @Override
    public Integer getArticleViewsCount() {
        Integer count = blogMapper.getBlogViewsCount();
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
            TbBlogTag entity = blogTagMapper.selectById(Integer.parseInt(split[i]));
            if (i == split.length - 1) {
                builder.append(entity.getTagName());
            } else {
                builder.append(entity.getTagName()).append(",");
            }
        }
        return builder.toString();
    }

    /**
     * 根据标签添加中间表数据
     */
    public void saveTagRelation(String tagsIds,Integer blogId) {
        QueryWrapper<TbBlogTagRelation> qw = new QueryWrapper<>();
        qw.lambda().eq(TbBlogTagRelation::getBlogId,blogId);
        String[] split = tagsIds.split(",");
        List<TbBlogTagRelation> all = tagRelationMapper.selectList(qw);
        all.forEach(
                t->{
                    tagRelationMapper.deleteById(t.getRelationId());
                }
        );
        List<TbBlogTagRelation> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            TbBlogTagRelation relation = new TbBlogTagRelation();
            relation.setBlogId(blogId);
            relation.setTagId(Integer.parseInt(split[i]));
            relation.setCreateTime(new Date());
            list.add(relation);
        }
        try {
            for (TbBlogTagRelation tagRelation : list) {
                tagRelationMapper.insert(tagRelation);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 添加观看人数
     * @param id
     */
    @Override
    public void updateBlogViews(Integer id) {
        TbBlog blog = blogMapper.selectById(id);
        Integer blogViews = blog.getBlogViews();
        blog.setBlogViews(blogViews + 1);
        blogMapper.updateById(blog);
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
        QueryWrapper<TbBlog> qw = new QueryWrapper<>();
        qw.orderByDesc("blog_views");
        List<TbBlog> blogs = blogMapper.selectList(qw);
        ArrayList<BlogVO> blogVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(blogs)){
            blogs.forEach(
                    blog->{
                        BlogVO blogVO = new BlogVO();
                        BeanUtils.copyProperties(blog,blogVO);
                        blogVOS.add(blogVO);
                    }
            );
        }

        return blogVOS.subList(0,4);
    }

    /**
     * 获取首页显示所有文章
     *
     * @return
     */
    @Override
    public List<BlogVO> getAllBlog() {

        /*Specification<TbBlog> spec = (root, criteriaQuery, criteriaBuilder) -> {
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
        return blogVOS;*/
        return null;
    }

    /**
     * 获取归档文章列表
     * @return
     */
    @Override
    public List<BlogVO> getArchiveBlog(){
        QueryWrapper<TbBlog> qw = new QueryWrapper<>();
        qw.orderByDesc("create_time");
        List<TbBlog> tbBlogs = blogMapper.selectList(qw);
        List<BlogVO> list = new ArrayList<>();
        tbBlogs.forEach(
                blog -> {
                    BlogVO vo = new BlogVO();
                    BeanUtils.copyProperties(blog,vo);
                    String time = null;
                    try {
                        time = DateUtils.parseDate2String(vo.getCreateTime(), "yyyy-MM-dd HH:mm");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    vo.setTime(time);
                    list.add(vo);
                }
        );
        return list;
    }

    /**
     * 分页查询首页数据
     * @param pageNum
     * @return
     */
    @Override
    public PageResult<BlogVO> getPageBlog(Integer pageNum) {
        QueryWrapper<TbBlog> qw = new QueryWrapper<>();
        qw.lambda().eq(TbBlog::getBlogStatus,1);
        qw.lambda().eq(TbBlog::getIsDeleted,0);
        qw.isNull("blog_sub_url");
        IPage<TbBlog> page = new Page(pageNum,2);
        IPage<TbBlog> iPage = blogMapper.selectPage(page, qw);
        List<TbBlog> lists = iPage.getRecords();
        if (lists != null && lists.size() > 0) {
            List<BlogVO> blogs = new ArrayList<>();
            if (!CollectionUtils.isEmpty(lists)){
                lists.forEach(
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
                            blogs.add(blogVO);

                        }
                );
            }
            return new PageResult<>(blogs,iPage.getSize(),iPage.getTotal());
        }
        return new PageResult<>(new ArrayList<>(),iPage.getSize(),iPage.getTotal());
    }

    /**
     * 根据url查询数据
     * @param url
     * @return
     */
    @Override
    public BlogVO getPageByUrl(String url) {
        QueryWrapper<TbBlog> qw = new QueryWrapper<>();
        qw.lambda().eq(TbBlog::getBlogSubUrl,url);
        TbBlog blog = blogMapper.selectOne(qw);
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

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @Override
    public BlogVO getBlogById(Integer id) {
        TbBlog blog = blogMapper.selectById(id);
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
