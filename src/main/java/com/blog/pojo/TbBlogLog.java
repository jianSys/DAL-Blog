package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @ProjectName: dal-blog
 * @ClassName: TbBlogLog
 * @Author: jian
 * @Description: 操作日志表
 * @Date: 2021/6/28 15:44
 * @Version: 1.0
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_blog_log")
public class TbBlogLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    /**
     * 操作内容
     */
    @Column(name = "operation")
    String operation;

    /**
     * 操作人
     */
    @Column(name = "operation_user")
    String operationUser;

    /**
     * 备注
     */
    @Column(name = "remark")
    String remark;
    /**
     * 操作时间
     */
    @Column(name = "create_time")
    Date createTime;
}
