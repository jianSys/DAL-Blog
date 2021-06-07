package com.blog.service;

import com.blog.pojo.TbBlogTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-06-03 22:04
 **/
public interface TagsService {
    Page<TbBlogTag> findTagByPage(Pageable pageable);

    TbBlogTag saveTags(TbBlogTag tbBlogTag);

    List<TbBlogTag> getAllTags();

}
