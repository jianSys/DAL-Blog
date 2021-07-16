package com.blog.service.impl;

import com.blog.dao.BlogFileDao;
import com.blog.pojo.TbBlogFile;
import com.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: dal-blog
 * @ClassName: FileServiceimpl
 * @Author: jian
 * @Description:
 * @Date: 2021/7/5 10:28
 * @Version: 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private BlogFileDao fileDao;

    @Override
    public Page<TbBlogFile> getFileListByPage(Pageable pageable) {
        Page<TbBlogFile> all = fileDao.findAll(pageable);
        return all;
    }

    @Override
    public TbBlogFile saveBlogFile(TbBlogFile tbBlogFile) {
        if (null == tbBlogFile){
            return null;
        }
        TbBlogFile save = fileDao.save(tbBlogFile);
        return save;
    }

    @Override
    public TbBlogFile getFileById(Integer id) {
        return fileDao.getOne(id);
    }
}
