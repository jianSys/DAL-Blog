package com.blog.dao;

import com.blog.pojo.TbBlogFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogFileDao
 * @Author: jian
 * @Description:
 * @Date: 2021/7/5 10:32
 * @Version: 1.0
 */
public interface BlogFileDao extends JpaRepository<TbBlogFile,Integer> {
}
