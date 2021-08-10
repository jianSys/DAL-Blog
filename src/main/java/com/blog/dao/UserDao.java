package com.blog.dao;

import com.blog.pojo.TbAdminUser;

import org.springframework.data.repository.query.Param;

/**
 * @ProjectName: dal-blog
 * @ClassName: UserDao
 * @Author: jian
 * @Description:
 * @Date: 2021/6/7 17:37
 * @Version: 1.0
 */
public interface UserDao {
    TbAdminUser findTbAdminUserByLoginUserName(@Param("loginUserName") String username);
}
