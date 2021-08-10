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
 * @ClassName: TbBlogConfig
 * @Author: jian
 * @Description: 配置类
 * @Date: 2021/6/16 9:37
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_config")
public class TbBlogConfig implements Serializable {
    /**
     * 配置名称
     */
    @TableId(type = IdType.AUTO)
    @TableField("config_name")
    private String configName;
    /**
     * 配置名称对应的值
     */
    @TableField("config_value")
    private String configValue;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
