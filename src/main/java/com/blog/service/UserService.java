package com.blog.service;

import com.blog.pojo.TbAdminUser;

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

    Boolean validation(String oldPassword);

    TbAdminUser updatePassword(String newPassword);
};
