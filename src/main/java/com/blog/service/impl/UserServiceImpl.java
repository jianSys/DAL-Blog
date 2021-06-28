package com.blog.service.impl;

import com.blog.commons.enums.LogEnum;
import com.blog.commons.utils.MD5Util;
import com.blog.dao.BlogLogDao;
import com.blog.dao.UserDao;
import com.blog.pojo.TbAdminUser;
import com.blog.pojo.TbBlogLog;
import com.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private BlogLogDao logDao;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public TbAdminUser login(String username, String password) {
        TbAdminUser user = userDao.findTbAdminUserByLoginUserName(username);
        String s = MD5Util.MD5Encode(password, "UTF-8");
        boolean b = s.equals(user.getLoginPassword());
        if (b){
            //添加操作日志
            logDao.save(
                    TbBlogLog.builder()
                            .operation(LogEnum.USER_LOGIN_OPERATION.getOperation())
                            .createTime(new Date())
                            .operationUser(username)
                            .build()
            );
            return user;
        }
        //添加操作日志
        logDao.save(
                TbBlogLog.builder()
                        .operation(LogEnum.USER_LOGIN_ERROR_OPERATION.getOperation())
                        .createTime(new Date())
                        .operationUser(username)
                        .build()
        );
        return null;
    }

    /**
     * 判断密码是否正确
     * @param oldPassword
     * @return
     */
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

    /**
     * 修改密码
     * @param newPassword
     * @return
     */
    @Override
    public TbAdminUser updatePassword(String newPassword) {
        String password = MD5Util.MD5Encode(newPassword, "UTF-8");
        TbAdminUser adminUser = userDao.findAll().get(0);
        adminUser.setLoginPassword(password);
        TbAdminUser user = userDao.save(adminUser);
        return user;
    }
}
