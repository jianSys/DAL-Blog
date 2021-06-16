package com.blog.service;

import com.blog.pojo.TbBlogConfig;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dal-blog
 * @ClassName: AdminService
 * @Author: jian
 * @Description:
 * @Date: 2021/6/16 9:32
 * @Version: 1.0
 */
public interface AdminService {
    Map<String,Object> getWebSite();
    List<TbBlogConfig> saveWebSite(Map<String,Object> map);
}
