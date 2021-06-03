package com.blog.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_blog_tag")
public class TbBlogTagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "create_time")
    private Date createTime;
}