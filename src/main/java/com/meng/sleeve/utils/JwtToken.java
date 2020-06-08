package com.meng.sleeve.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.meng.sleeve.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtToken {

    private static String jwtKey;

    @Value("${sleeve.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtToken.jwtKey = jwtKey;
    }

    private static Integer expiredTimeIn;

    @Value("${sleeve.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    //   生成jwt token
    public static String makeToken(Long uid, Integer scope) {
        return getToken(uid, scope);
    }

    public static String makeToken(Long uid) {
        return getToken(uid, 8);
    }

    //    验证是否为jwt token
    public static Optional<Map<String, Claim>> getClaim(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
        return Optional.of(decodedJWT.getClaims());
    }


    //    校验token是否合法
    public static Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;
    }


    private static String getToken(Long uid, Integer scope) {
//        算法
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
//        创建jwt
        String token = JWT.create()
                .withClaim("uid", uid)
                .withClaim("scope", scope)
//                过期时间
                .withExpiresAt(CalendarExpiredAndIssued().get("expiredTime"))
//                签发时间
                .withIssuedAt(CalendarExpiredAndIssued().get("now"))
//                生成jwt
                .sign(algorithm);
        return token;
    }

    //    生成签发和过期时间
    private static Map<String, Date> CalendarExpiredAndIssued() {

        Calendar calendar = Calendar.getInstance();
//        当前时间
        Date now = calendar.getTime();
//        过期时间
        calendar.add(Calendar.SECOND, expiredTimeIn);

        Map<String, Date> map = new HashMap<>();
        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }


}
