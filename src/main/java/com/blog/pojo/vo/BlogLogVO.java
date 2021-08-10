package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-06-28 21:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogLogVO {

    Integer id;

    /**
     * 操作内容
     */

    String operation;

    /**
     * 操作人
     */

    String operationUser;

    /**
     * 备注
     */

    String remark;
    /**
     * 操作时间
     */

    Date createTime;
    /**
     * 页面时间显示
     */
    String time;
}
