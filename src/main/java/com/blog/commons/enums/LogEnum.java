package com.blog.commons.enums;

import lombok.Getter;

@Getter
public enum LogEnum {


    ARTICLE_UPDATE_OPERATION("文章修改"),
    ARTICLE_ADD_OPERATION("文章发布"),
    ARTICLE_DELETE_OPERATION("文章删除"),
    WEBSITE_UPDATE_OPERATION("设置修改"),
    USER_LOGIN_OPERATION("用户登录"),
    USER_LOGOUT_OPERATION("用户注销"),
    USER_LOGIN_ERROR_OPERATION("登录失败");

    private String operation;

    LogEnum(String operation) {
        this.operation = operation;
    }
}
