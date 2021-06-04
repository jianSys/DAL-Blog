package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @ProjectName: springboot
 * @ClassName: TbBlog
 * @Author: jian
 * @Description: 文章
 * @Date: 2021/5/27 15:49
 * @Version: 1.0
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_blog")
public class TbBlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    Integer blogId;
    @Column(name = "blog_title")
    String  blogTitle;
    /**
     * 自定义路径
     */
    @Column(name = "blog_sub_url")
    String  blogSubUrl;
    /**
     * 博客封面图
     */
    @Column(name = "blog_cover_image")
    String blogCoverImage;
    /**
     * 博客内容
     */
    @Column(name = "blog_content")
    String blogContent;
    /**
     * 分类id
     */
    @Column(name = "blog_category_id")
    Integer blogCategoryId;
    /**
     * 分类名称
     */
    @Column(name = "blog_category_name")
    String blogCategoryName;
    /**
     * 标签
     */
    @Column(name = "blog_tags")
    String blogTags;
    /**
     * 状态 0-草稿,1,发布
     */
    @Column(name = "blog_status")
    Integer blogStatus;
    /**
     * 阅读量
     */
    @Column(name = "blog_views")
    Integer blogViews;
    /**
     * 是否允许评论0-允许,1-不允许
     */
    @Column(name = "enable_comment")
    Integer enableComment;
    /**
     * 是否置顶
     */
    @Column(name="blog_top")
    Integer blogTop;
    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    Integer isDeleted;
    @Column(name = "create_time")
    Date createTime;
    @Column(name = "update_time")
    Date updateTime;
}
