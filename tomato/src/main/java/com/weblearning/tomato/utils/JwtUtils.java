package com.weblearning.tomato.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    private static String secretKey = "EdwinNullIsHere";
    public static String getJwt(Map<String, Object> map) {
        Calendar instance = Calendar.getInstance();//获取当前时间
        instance.add(Calendar.HOUR, 3);//设定令牌过期时间
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            builder.withClaim(k, v.toString());
        });
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(secretKey));
        //指定编码方式，进行签名
        return token;
    }
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
    }
}
