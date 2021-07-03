package com.blog.service.impl;

import com.blog.dao.BlogThemeDao;
import com.blog.pojo.TbBlogTheme;
import com.blog.service.ThemeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-07-03 18:26
 **/
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BlogThemeDao themeDao;
    @Override
    public List<TbBlogTheme> getAllTheme() {
        List<TbBlogTheme> all = themeDao.findAll();
        return all;
    }

    /**
     * 修改主题
     * @param id
     */
    @Override
    public void replaceTheme(Integer id) {
        String value = (String) redisTemplate.opsForValue().get("theme");
        if (StringUtils.isNotBlank(value)) {
            Boolean theme = redisTemplate.delete("theme");
        }
        //获取已经启动的主题
        this.closeTheme();

        TbBlogTheme theme = themeDao.getOne(id);
        theme.setStatus(1);
        TbBlogTheme blogTheme = themeDao.save(theme);
        //加入redis
        redisTemplate.opsForValue().set("theme",blogTheme.getFolderName());
    }
    public void closeTheme(){
        TbBlogTheme upTheme = themeDao.findUpTheme();
        upTheme.setStatus(0);
        themeDao.save(upTheme);
    }
}
