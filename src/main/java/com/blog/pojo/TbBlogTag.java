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


@Data

@NoArgsConstructor
@AllArgsConstructor
@TableName( "tb_blog_tag")
public class TbBlogTag implements Serializable {

    @TableId(type = IdType.AUTO)
    @TableField( "tag_id")
    private Integer tagId;

    @TableField( "tag_name")
    private String tagName;

    @TableField( "is_deleted")
    private Integer isDeleted;

    @TableField( "create_time")
    private Date createTime;
}