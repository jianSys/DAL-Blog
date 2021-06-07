package com.blog.pojo;

/**
 * @program: SpringBoot
 * @description:
 * @author: jian
 * @create: 2021-05-23 15:04
 **/
public class LoginUser {

    private String accessToken;

    private String username;

    private Integer id;

    private String phone;

    public LoginUser() {
    }

    public LoginUser(String accessToken, String username, Integer id, String phone) {
        this.accessToken = accessToken;
        this.username = username;
        this.id = id;
        this.phone = phone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
