package com.blog.pojo;

/**
 * @program: SpringBoot
 * @description:
 * @author: jian
 * @create: 2021-05-23 14:15
 **/
public class User {
    String username;
    String password;
    String vercode;

    public User() {
    }

    public User(String username, String password,String vercode) {
        this.username = username;
        this.password = password;
        this.vercode = vercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }



    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vercode='" + vercode + '\'' +
                '}';
    }
}
