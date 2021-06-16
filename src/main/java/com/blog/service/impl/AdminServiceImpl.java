package com.blog.service.impl;

import com.blog.dao.BlogConfigDao;
import com.blog.pojo.TbBlogConfig;
import com.blog.service.AdminService;
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
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private BlogConfigDao configDao;

    @Override
    public Map<String, Object> getWebSite() {
        List<TbBlogConfig> all = configDao.findAll();
        Map<String, Object> map = new HashMap<>();
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
        System.out.println(daoAll);
      /*  for (TbBlogConfig config : daoAll) {
            Object o = map.get(config.getConfigName());
            if (null!=o){
                config.setConfigValue(o.toString());
                config.setUpdateTime(new Date());
            }
        }*/
        daoAll.forEach(
                c -> {
                    Object value = map.get(c.getConfigName());
                    if (null != value) {
                        c.setConfigValue(value.toString());
                        c.setUpdateTime(new Date());
                    }
                });

        List<TbBlogConfig> all = configDao.saveAll(daoAll);
        return all;
    }

}
