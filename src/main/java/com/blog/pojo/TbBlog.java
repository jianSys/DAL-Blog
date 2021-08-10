package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_blog")
public class TbBlog {
    @TableId
    @TableField("blog_id")
    Integer blogId;
    @TableField("blog_title")
    String blogTitle;
    /**
     * 自定义路径
     */
    @TableField("blog_sub_url")
    String blogSubUrl;
    /**
     * 博客封面图
     */
    @TableField("blog_cover_image")
    String blogCoverImage;
    /**
     * 博客内容
     */
    @TableField("blog_content")
    String blogContent;
    /**
     * 分类id
     */
    @TableField("blog_category_id")
    Integer blogCategoryId;
    /**
     * 分类名称
     */
    @TableField("blog_category_name")
    String blogCategoryName;
    /**
     * 标签
     */
    @TableField("blog_tags")
    String blogTags;
    /**
     * 状态 0-草稿,1,发布
     */
    @TableField("blog_status")
    Integer blogStatus;
    /**
     * 阅读量
     */
    @TableField("blog_views")
    Integer blogViews;
    /**
     * 是否允许评论0-允许,1-不允许
     */
    @TableField("enable_comment")
    Integer enableComment;
    /**
     * 是否置顶
     */
    @TableField("blog_top")
    Integer blogTop;
    /**
     * 文章简介
     */
    @TableField("blog_introduce")
    String blogIntroduce;
    /**
     * 是否删除
     */
    @TableField("is_deleted")
    Integer isDeleted;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
