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
 * @ClassName: TbBlogLog
 * @Author: jian
 * @Description: 操作日志表
 * @Date: 2021/6/28 15:44
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName( "tb_blog_log")
public class TbBlogLog {
    @TableId(type = IdType.AUTO)
    @TableField( "id")
    Integer id;

    /**
     * 操作内容
     */
    @TableField( "operation")
    String operation;

    /**
     * 操作人
     */
    @TableField( "operation_user")
    String operationUser;

    /**
     * 备注
     */
    @TableField( "remark")
    String remark;
    /**
     * 操作时间
     */
    @TableField( "create_time")
    Date createTime;
}
