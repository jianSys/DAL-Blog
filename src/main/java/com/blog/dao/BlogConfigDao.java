package com.blog.dao;

import com.blog.pojo.TbBlogConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogConfig
 * @Author: jian
 * @Description:
 * @Date: 2021/6/16 9:33
 * @Version: 1.0
 */
public interface BlogConfigDao extends JpaRepository<TbBlogConfig,String> {
}
