package com.blog.service.impl;

import com.blog.commons.utils.MD5Util;
import com.blog.dao.UserDao;
import com.blog.pojo.TbAdminUser;
import com.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: dal-blog
 * @ClassName: UserServiceImpl
 * @Author: jian
 * @Description:
 * @Date: 2021/6/7 17:35
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public TbAdminUser login(String username, String password) {
        TbAdminUser user = userDao.findTbAdminUserByLoginUserName(username);
        String s = MD5Util.MD5Encode(password, "UTF-8");
        boolean b = s.equals(user.getLoginPassword());
        if (b){
            return user;
        }
        return null;
    }

    @Override
    public Boolean validation(String oldPassword) {
        String loginPassword = userDao.findAll().get(0).getLoginPassword();
        String s = MD5Util.MD5Encode(oldPassword, "UTF-8");
        boolean b = s.equals(loginPassword);
        if (b){
            return true;
        }
        return false;
    }


    @Override
    public TbAdminUser updatePassword(String newPassword) {
        String password = MD5Util.MD5Encode(newPassword, "UTF-8");
        TbAdminUser adminUser = userDao.findAll().get(0);
        adminUser.setLoginPassword(password);
        TbAdminUser user = userDao.save(adminUser);
        return user;
    }
}
