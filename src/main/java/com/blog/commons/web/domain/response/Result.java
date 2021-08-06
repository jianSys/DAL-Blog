package com.blog.commons.web.domain.response;

/**
 * @program: SpringBoot
 * @description:
 * @author: jian
 * @create: 2021-05-23 15:30
 **/

public class Result {
    Integer code;
    String msg;
    Integer count;
    Object data;

    public Result() {
    }
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;

    }
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 操作成功返回
     * @param message
     * @return
     */
    public static Result ok(String message){
        return new Result(0,message);
    }
    /**
     * 带参数返回
     * @param message
     * @return
     */
    public static Result ok(String message,Object data){
        return new Result(0,message,data);
    }

    /**
     * 错误返回
     * @param message
     * @return
     */
    public static Result error(String message){
        return new Result(500,message);
    }

    /**
     * 自定义错误码返回
     * @param code
     * @param message
     * @return
     */
    public static Result error(Integer code , String message){
        return new Result(code,message);
    }

}


