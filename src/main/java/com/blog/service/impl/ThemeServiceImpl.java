package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.dao.BlogThemeDao;
import com.blog.mapper.BlogThemeMapper;
import com.blog.pojo.TbBlogTheme;
import com.blog.service.ThemeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: dal-blog
 * @description:
 * @author: jian
 * @create: 2021-07-03 18:26
 **/
@Service
public class ThemeServiceImpl implements ThemeService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private BlogThemeMapper themeMapper;

    /**
     * 查询所有的主题
     * @return
     */
    @Override
    public List<TbBlogTheme> getAllTheme() {
        List<TbBlogTheme> themeList = themeMapper.findAll();
        return themeList;
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

        TbBlogTheme theme = themeMapper.selectById(id);
        theme.setStatus(1);
        int blogTheme = themeMapper.updateById(theme);
        //加入redis
        redisTemplate.opsForValue().set("theme",theme.getFolderName());
    }
    public void closeTheme(){
        QueryWrapper<TbBlogTheme> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbBlogTheme::getStatus,1);
        TbBlogTheme upTheme = themeMapper.selectOne(wrapper);
        upTheme.setStatus(0);
        themeMapper.updateById(upTheme);
    }
}
