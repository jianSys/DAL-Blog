package com.blog.commons.web.domain.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: dal-blog
 * @description: 分页返回
 * @author: jian
 * @create: 2021-08-10 21:00
 **/
public class PageResult<T> implements Serializable {
    //总页数
    private Long totalPages;
    //总条数
    private Long total;
    //列表数据
    private List<T> data;

    /**
     * 分页
     *
     * @param data       列表数据
     * @param currPage   当前页数
     */
    public PageResult(List<T> data, Long totalPages, Long total) {
        this.data = data;
        this.totalPages = totalPages;
        this.total = total;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
