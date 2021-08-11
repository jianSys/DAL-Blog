package com.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.commons.utils.ConvertUtils;
import com.blog.commons.utils.DateUtils;
import com.blog.dao.BlogLogDao;
import com.blog.mapper.BlogLogMapper;
import com.blog.pojo.TbBlogLog;
import com.blog.pojo.vo.BlogLogVO;
import com.blog.service.LogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private BlogLogMapper logMapper;

    @Override
    public List<BlogLogVO> getLatestLog() {
        IPage<TbBlogLog> page = new Page(0,5);
        QueryWrapper<TbBlogLog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        IPage<TbBlogLog> iPage = logMapper.selectPage(page, wrapper);
        List<TbBlogLog> logList = iPage.getRecords();

        List<BlogLogVO> list = new ArrayList<>();
        for (TbBlogLog log : logList) {
            BlogLogVO vo = new BlogLogVO();
            BeanUtils.copyProperties(log,vo);
            //封装时间
            String ago = DateUtils.getTimeAgo(vo.getCreateTime());
            vo.setTime(ago);
            list.add(vo);
        }
        return list;
    }
}
