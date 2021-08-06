package com.blog.commons.web.domain.response;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @ProjectName: dal-blog
 * @ClassName: ResultController
 * @Author: jian
 * @Description:
 * @Date: 2021/8/6 13:40
 * @Version: 1.0
 */
public class ResultController {
    /**
     * 页面跳转
     * */
    public ModelAndView jumpPage(String path){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        return modelAndView;
    }

    /**
     * 带参数的页面跳转
     * */
    public ModelAndView jumpPage(String path, Map<String,?> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }
}
