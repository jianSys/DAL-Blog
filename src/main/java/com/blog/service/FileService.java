package com.blog.service;

import com.blog.pojo.TbBlogFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ProjectName: dal-blog
 * @ClassName: FileService
 * @Author: jian
 * @Description: 博客文件
 * @Date: 2021/7/5 10:25
 * @Version: 1.0
 */
public interface FileService {
    /**
     * 分页查询
     */
    Page<TbBlogFile> getFileListByPage(Pageable pageable);
    /**
     * 保存数据
     */
    TbBlogFile saveBlogFile(TbBlogFile tbBlogFile);
    /**
     * 根据id获取
     */
    TbBlogFile getFileById(Integer id);
}
