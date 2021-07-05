package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_blog_file")
public class TbBlogFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    Integer fileId;
    /**
     * 文件名称
     */
    @Column(name = "file_name")
    String fileName;
    /**
     * 文件类型
     */
    @Column(name = "file_type")
    String fileType;
    /**
     * 文件地址
     */
    @Column(name = "file_url")
    String fileUrl;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;
}
