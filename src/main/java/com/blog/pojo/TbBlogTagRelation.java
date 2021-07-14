package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: dal-blog
 * @ClassName: TbBlogTagRelation
 * @Author: jian
 * @Description: 标签文章标签表
 * @Date: 2021/7/14 16:03
 * @Version: 1.0
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_blog_tag_relation")
public class TbBlogTagRelation implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private Integer relationId;
    /**
     * 文章id
     */
    @Column(name = "blog_id")
    private Integer blogId;
    /**
     * 标签id
     */
    @Column(name = "tag_id")
    private Integer tagId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
