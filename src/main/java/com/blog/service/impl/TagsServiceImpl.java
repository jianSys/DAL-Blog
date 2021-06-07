package com.blog.service.impl;

import com.blog.dao.BlogTagDao;
import com.blog.pojo.TbBlogTag;
import com.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-06-03 22:04
 **/
@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private BlogTagDao tagDao;

    /**
     * 分页查询所有博客标签
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<TbBlogTag> findTagByPage(Pageable pageable) {
        Page<TbBlogTag> all = tagDao.findAll(pageable);
        return all;
    }

    /**
     * 添加标签数据
     *
     * @param tbBlogTag
     */
    @Override
    public TbBlogTag saveTags(TbBlogTag tbBlogTag) {
        Integer tagId = tbBlogTag.getTagId();
        if (null != tagId) {
            TbBlogTag entity = tagDao.save(tbBlogTag);
            return entity;
        } else {
            tbBlogTag.setCreateTime(new Date());
            tbBlogTag.setIsDeleted(0);
            TbBlogTag tagEntity = tagDao.save(tbBlogTag);
            return tagEntity;
        }
    }

    @Override
    public List<TbBlogTag> getAllTags() {
        return tagDao.findAll();
    }
}
