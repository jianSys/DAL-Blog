package com.blog.service.impl;

import com.blog.commons.enums.LogEnum;
import com.blog.dao.BlogConfigDao;
import com.blog.dao.BlogLogDao;
import com.blog.pojo.TbBlogConfig;
import com.blog.pojo.TbBlogLog;
import com.blog.service.AdminService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ProjectName: dal-blog
 * @ClassName: AdminServiceImpl
 * @Author: jian
 * @Description:
 * @Date: 2021/6/16 9:33
 * @Version: 1.0
 */
@Log4j2
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private BlogConfigDao configDao;

    @Autowired
    private BlogLogDao logDao;

    @Override
    public Map<String, String> getWebSite() {
        List<TbBlogConfig> all = configDao.findAll();
        Map<String, String> map = new HashMap<>();
        all.forEach(
                config -> {
                    map.put(config.getConfigName(), config.getConfigValue());
                }
        );
        return map;
    }

    @Override
    public List<TbBlogConfig> saveWebSite(Map<String, Object> map) {
        List<TbBlogConfig> daoAll = configDao.findAll();
        daoAll.forEach(
                c -> {
                    Object value = map.get(c.getConfigName());
                    if (null != value) {
                        c.setConfigValue(value.toString());
                        c.setUpdateTime(new Date());
                    }
                });
        try {
            List<TbBlogConfig> all = configDao.saveAll(daoAll);
            //保存修改日志
            logDao.save(
                    TbBlogLog.builder()
                            .operation(LogEnum.WEBSITE_UPDATE_OPERATION.getOperation())
                            .createTime(new Date())
                            .build()
            );
            return all;
        } catch (Exception e) {
            log.error("==========================修改网站设置异常======================", e);
            return null;
        }


    }

}
