package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @program: dal-blog
 * @description: 主题
 * @author: jian
 * @create: 2021-07-03 18:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName( "tb_blog_theme")
public class TbBlogTheme {
    @TableId(type = IdType.AUTO)
    @TableField( "id")
    private Integer id;
    /**
     * 名称
     */
    @TableField( "name")
    private String name;
    /**
     * 图片地址
     */
    @TableField( "img_url")
    private String imgUrl;
    /**
     * 状态
     */
    @TableField( "status")
    private Integer status;
    /**
     * 描述
     */
    @TableField( "description")
    private String description;
    /**
     * 文件夹名称
     */
    @TableField( "folder_name")
    private String folderName;
}
