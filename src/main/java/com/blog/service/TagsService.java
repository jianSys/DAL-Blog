package com.blog.service;

import com.blog.pojo.TbBlogTagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-06-03 22:04
 **/
public interface TagsService {
    Page<TbBlogTagEntity> findTagByPage(Pageable pageable);

    TbBlogTagEntity saveTags(TbBlogTagEntity tbBlogTagEntity);
}
