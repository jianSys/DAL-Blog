package com.blog.service.impl;

import com.blog.dao.BlogTagDao;
import com.blog.pojo.TbBlogTagEntity;
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
    public Page<TbBlogTagEntity> findTagByPage(Pageable pageable) {
        Page<TbBlogTagEntity> all = tagDao.findAll(pageable);
        return all;
    }

    /**
     * 添加标签数据
     *
     * @param tbBlogTagEntity
     */
    @Override
    public TbBlogTagEntity saveTags(TbBlogTagEntity tbBlogTagEntity) {
        Integer tagId = tbBlogTagEntity.getTagId();
        if (null != tagId) {
            TbBlogTagEntity entity = tagDao.save(tbBlogTagEntity);
            return entity;
        } else {
            tbBlogTagEntity.setCreateTime(new Date());
            tbBlogTagEntity.setIsDeleted(0);
            TbBlogTagEntity tagEntity = tagDao.save(tbBlogTagEntity);
            return tagEntity;
        }
    }

    @Override
    public List<TbBlogTagEntity> getAllTags() {
        return tagDao.findAll();
    }
}
