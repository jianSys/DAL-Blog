package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogVO
 * @Author: jian
 * @Description: 博客
 * @Date: 2021/6/29 9:55
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO {
    Integer blogId;

    String blogTitle;
    /**
     * 自定义路径
     */
    String blogSubUrl;
    /**
     * 博客封面图
     */
    String blogCoverImage;
    /**
     * 博客内容
     */
    String blogContent;
    /**
     * 分类id
     */
    Integer blogCategoryId;
    /**
     * 分类名称
     */
    String blogCategoryName;
    /**
     * 标签
     */
    String blogTags;
    /**
     * 状态 0-草稿,1,发布
     */
    Integer blogStatus;
    /**
     * 阅读量
     */
    Integer blogViews;
    /**
     * 是否允许评论0-允许,1-不允许
     */
    Integer enableComment;
    /**
     * 是否置顶
     */
    Integer blogTop;
    /**
     * 文章简介
     */
    String blogIntroduce;
    /**
     * 是否删除
     */
    Integer isDeleted;
    Date createTime;
    Date updateTime;
    /**
     * 年月
     */
    String time;
    /**
     * 日
     */
    String day;
}
