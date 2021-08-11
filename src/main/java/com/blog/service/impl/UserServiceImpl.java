package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.commons.enums.LogEnum;
import com.blog.commons.utils.MD5Util;
import com.blog.dao.BlogLogDao;
import com.blog.dao.UserDao;
import com.blog.mapper.BlogLogMapper;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.BlogUserMapper;
import com.blog.pojo.TbAdminUser;
import com.blog.pojo.TbBlogLog;
import com.blog.pojo.User;
import com.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private BlogUserMapper userMapper;

    @Resource
    private BlogLogMapper logMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public TbAdminUser login(String username, String password) {
        QueryWrapper<TbAdminUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbAdminUser::getLoginUserName,username);
        TbAdminUser user = userMapper.selectOne(wrapper);
        if (null == user){
            return null;
        }
        String s = MD5Util.MD5Encode(password, "UTF-8");
        boolean b = s.equals(user.getLoginPassword());
        if (b){
            //添加操作日志
            logMapper.insert(
                    TbBlogLog.builder()
                            .operation(LogEnum.USER_LOGIN_OPERATION.getOperation())
                            .createTime(new Date())
                            .operationUser(username)
                            .build()
            );
            return user;
        }
        //添加操作日志
        logMapper.insert(
                TbBlogLog.builder()
                        .operation(LogEnum.USER_LOGIN_ERROR_OPERATION.getOperation())
                        .createTime(new Date())
                        .operationUser(username)
                        .build()
        );
        return user;
    }

    /**
     * 判断密码是否正确
     * @param oldPassword
     * @return
     */
    @Override
    public Boolean validation(String username ,String oldPassword) {
        QueryWrapper<TbAdminUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbAdminUser::getLoginUserName,username);
        TbAdminUser user = userMapper.selectOne(wrapper);
        String s = MD5Util.MD5Encode(oldPassword, "UTF-8");
        boolean b = s.equals(user.getLoginPassword());
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
    public void updatePassword(String newPassword) {
        String password = MD5Util.MD5Encode(newPassword, "UTF-8");
        TbAdminUser adminUser = userMapper.findAll().get(0);
        adminUser.setLoginPassword(password);
        userMapper.insert(adminUser);
    }

    @Override
    public TbAdminUser getUserInfo(String username) {
        QueryWrapper<TbAdminUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            wrapper.lambda().eq(TbAdminUser::getLoginUserName,username);
        }
        try{
            TbAdminUser user = userMapper.selectOne(wrapper);
            return user;
        }catch (Exception e){
            return null;
        }
    }
}
