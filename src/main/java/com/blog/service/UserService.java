package com.blog.service;

import com.blog.pojo.TbAdminUser;
import com.blog.pojo.User;

/**
 * @ProjectName: dal-blog
 * @ClassName: UserService
 * @Author: jian
 * @Description:
 * @Date: 2021/6/7 17:35
 * @Version: 1.0
 */
public interface UserService {

    TbAdminUser login(String username,String password);

    Boolean validation(String username,String oldPassword);

    TbAdminUser updatePassword(String newPassword);

    TbAdminUser getUserInfo(String username);
};
