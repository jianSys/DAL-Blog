package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.TbBlogConfig;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogConfigMapper
 * @Author: jian
 * @Description: 博客配置类
 * @Date: 2021/8/10 9:43
 * @Version: 1.0
 */
public interface BlogConfigMapper extends BaseMapper<TbBlogConfig> {
    @Select("select * from tb_config")
    List<TbBlogConfig> findAll();
}
