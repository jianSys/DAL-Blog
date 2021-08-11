package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.TbAdminUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogUserMapper
 * @Author: jian
 * @Description: 博客登录用户
 * @Date: 2021/8/10 10:39
 * @Version: 1.0
 */
public interface BlogUserMapper extends BaseMapper<TbAdminUser> {
    /**
     * 查询登录用户
     */
    @Select("select * from tb_admin_user")
    List<TbAdminUser> findAll();
}
