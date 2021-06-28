package com.blog.service.impl;

import com.blog.dao.BlogLogDao;
import com.blog.pojo.TbBlogLog;
import com.blog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: LogServiceImpl
 * @Author: jian
 * @Description:
 * @Date: 2021/6/28 17:37
 * @Version: 1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private BlogLogDao logDao;
    @Override
    public List<TbBlogLog> getLatestLog() {
        return logDao.findAll();
    }
}
