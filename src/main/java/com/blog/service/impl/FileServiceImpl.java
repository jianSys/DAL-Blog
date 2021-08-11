package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.dao.BlogFileDao;
import com.blog.mapper.BlogFileMapper;
import com.blog.pojo.TbBlogFile;
import com.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private BlogFileMapper fileMapper;

    @Override
    public List<TbBlogFile> getFileListByPage(Pageable pageable) {
        QueryWrapper<TbBlogFile> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<TbBlogFile> fileList = fileMapper.selectList(wrapper);
        return fileList;
    }

    @Override
    public void saveBlogFile(TbBlogFile tbBlogFile) {
        fileMapper.insert(tbBlogFile);
    }

    @Override
    public TbBlogFile getFileById(Integer id) {
        TbBlogFile file = fileMapper.selectById(id);
        return file;
    }
}
