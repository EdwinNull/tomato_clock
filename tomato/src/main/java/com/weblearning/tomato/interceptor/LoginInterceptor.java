package com.weblearning.tomato.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.weblearning.tomato.pojo.Result;
import com.weblearning.tomato.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String url = req.getRequestURI();
        log.info("request url: {}", url);
        if(url.contains("login")) {
            log.info("Logined");
            return true;
        }
        String jwt = req.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("token is empty");
            Result error = Result.error("not login");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        try{
            JwtUtils.verify(jwt);
        } catch (Exception e){
            log.info("verify fail");
            Result error = Result.error("not login");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        log.info("放行");
        return true;
    }
}
