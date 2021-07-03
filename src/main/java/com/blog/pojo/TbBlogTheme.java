package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @program: dal-blog
 * @description: 主题
 * @author: jian
 * @create: 2021-07-03 18:33
 **/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_blog_theme")
public class TbBlogTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 图片地址
     */
    @Column(name = "img_url")
    private String imgUrl;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 文件夹名称
     */
    @Column(name = "folder_name")
    private String folderName;
}
