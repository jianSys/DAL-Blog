package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * @ProjectName: dal-blog
 * @ClassName: TbBlogFile
 * @Author: jian
 * @Description: 博客文件
 * @Date: 2021/7/5 10:20
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName( "tb_blog_file")
public class TbBlogFile {
    @TableId(type = IdType.AUTO)
    @TableField( "file_id")
    Integer fileId;
    /**
     * 文件名称
     */
    @TableField( "file_name")
    String fileName;
    /**
     * 文件类型
     */
    @TableField( "file_type")
    String fileType;
    /**
     * 文件地址
     */
    @TableField( "file_url")
    String fileUrl;
    /**
     * 创建时间
     */
    @TableField( "create_time")
    Date createTime;
}
