package com.blog.controller.admin;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: dal-blog
 * @description: 验证码
 * @author: jian
 * @create: 2021-08-07 00:06
 **/
@RestController
@RequestMapping("admin/captcha")
public class CaptchaController {

    /**
     * 验证码生成
     *
     * @param request  请求报文
     * @param response 响应报文
     */
    @RequestMapping("generate")
    public void generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
