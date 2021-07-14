package com.blog.dao;

import com.blog.pojo.TbBlogTagRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogTagRelationDao
 * @Author: jian
 * @Description: 标签文章中间表
 * @Date: 2021/7/14 16:12
 * @Version: 1.0
 */
public interface BlogTagRelationDao extends JpaRepository<TbBlogTagRelation,Integer> {

    List<TbBlogTagRelation> findAllByBlogId(Integer blogId);
}
