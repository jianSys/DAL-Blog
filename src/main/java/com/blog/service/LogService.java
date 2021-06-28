package com.blog.service;

import com.blog.pojo.TbBlogLog;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: LogService
 * @Author: jian
 * @Description: 日志
 * @Date: 2021/6/28 17:37
 * @Version: 1.0
 */
public interface LogService {
    /**
     * 获取最新的日志
     * @return
     */
    List<TbBlogLog> getLatestLog();
}
