package com.blog.service;

import com.blog.pojo.TbBlog;
import org.hibernate.hql.spi.id.TableBasedDeleteHandlerImpl;

/**
 * @ProjectName: dal-blog
 * @ClassName: PageService
 * @Author: jian
 * @Description: 页面操作
 * @Date: 2021/6/29 16:17
 * @Version: 1.0
 */
public interface PageService {
    TbBlog savePage(TbBlog tbBlog);
}
