package com.weblearning.tomato.filter;

import com.alibaba.fastjson.JSONObject;
import com.weblearning.tomato.pojo.Result;
import com.weblearning.tomato.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取请求url
        String url = req.getRequestURL().toString();
        log.info("URL: {}", url);
        //是否有login，是则方向
        if(url.contains("login")){
            log.info("Logined");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //获取令牌
        String jwt = req.getHeader("token");
        //判断令牌合法性
        if(!StringUtils.hasLength(jwt)){
            log.info("Token is empty");
            Result error = Result.error("你还没有登陆.");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //存在，则进行解析
        try {
            JwtUtils.verify(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回失败信息");
            Result error = Result.error("Not login");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //放行
        log.info("Logined");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
