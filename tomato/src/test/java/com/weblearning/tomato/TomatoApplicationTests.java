package com.weblearning.tomato;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class TomatoApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 3600);//失效时间
        String token = JWT.create()
                .withHeader(map)//可省略
                .withClaim("id", 12)
                .withClaim("username", "Edwin")
                //设定要编码的对象
                .withExpiresAt(instance.getTime())
                //令牌过期的时间
                .sign(Algorithm.HMAC256("EDWINNULLHERE"));
        //指定编码形式
        System.out.println(token);
    }

    @Test
    public void test() {
        //解码过程
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("EDWINNULLHERE")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsImV4cCI6MTcxNTYwMjI3NywidXNlcm5hbWUiOiJFZHdpbiJ9.TjVDQ3SwIPyZinjRI2nIVPsmXEELfyLW7EPs4kp_k74");
        System.out.println(verify.getClaim("id"));
        System.out.println(verify.getClaim("username"));
        System.out.println(verify.getExpiresAt());
    }
}
