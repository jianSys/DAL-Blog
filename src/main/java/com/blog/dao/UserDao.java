package com.blog.dao;

import com.blog.pojo.TbAdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @ProjectName: dal-blog
 * @ClassName: UserDao
 * @Author: jian
 * @Description:
 * @Date: 2021/6/7 17:37
 * @Version: 1.0
 */
public interface UserDao extends JpaRepository<TbAdminUser,Integer> {
    TbAdminUser findTbAdminUserByLoginUserName(@Param("loginUserName") String username);
}
