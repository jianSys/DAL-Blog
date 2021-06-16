package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: dal-blog
 * @ClassName: TbBlogConfig
 * @Author: jian
 * @Description: 配置类
 * @Date: 2021/6/16 9:37
 * @Version: 1.0
 */
@Entity
@Data
@AllArgsConstructor
@DynamicUpdate
@NoArgsConstructor
@Table(name = "tb_config")
public class TbBlogConfig implements Serializable {
    /**
     * 配置名称
     */
    @Id
    @Column(name = "config_name")
    private String configName;
    /**
     * 配置名称对应的值
     */
    @Column(name = "config_value")
    private String configValue;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}
