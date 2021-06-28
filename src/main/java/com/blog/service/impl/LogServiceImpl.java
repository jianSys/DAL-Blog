package com.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.blog.commons.utils.ConvertUtils;
import com.blog.commons.utils.DateUtils;
import com.blog.dao.BlogLogDao;
import com.blog.pojo.TbBlogLog;
import com.blog.pojo.vo.BlogLogVO;
import com.blog.service.LogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
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
    public List<BlogLogVO> getLatestLog() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(0, 5,sort);
        Page<TbBlogLog> page = logDao.findAll(pageable);
        List<TbBlogLog> content = page.getContent();
        ArrayList<BlogLogVO> logList = new ArrayList<>();
        for (TbBlogLog log : content) {
            BlogLogVO vo = new BlogLogVO();
            BeanUtils.copyProperties(log,vo);
            //封装时间
            String ago = DateUtils.getTimeAgo(vo.getCreateTime());
            vo.setTime(ago);
            logList.add(vo);
        }
        return logList;
    }
}
