package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
@NoArgsConstructor
@AllArgsConstructor
@TableName( "tb_blog_tag_relation")
public class TbBlogTagRelation implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @TableField( "relation_id")
    private Integer relationId;
    /**
     * 文章id
     */
    @TableField( "blog_id")
    private Integer blogId;
    /**
     * 标签id
     */
    @TableField( "tag_id")
    private Integer tagId;
    /**
     * 创建时间
     */
    @TableField( "create_time")
    private Date createTime;
}
