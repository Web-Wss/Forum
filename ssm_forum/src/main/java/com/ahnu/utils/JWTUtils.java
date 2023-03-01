package com.ahnu.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String SECRET = "#^%*hello";

    public static String generateToken(String acct) {
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("acct",acct);
        JwtBuilder jwtBuilder = Jwts.builder();
//        签发算法，设置密钥
        jwtBuilder.signWith(SignatureAlgorithm.HS256,SECRET);
//        body数据，要唯一，自行设置
        jwtBuilder.addClaims(claims);
//        设置签发时间
        jwtBuilder.setIssuedAt(new Date());
//        设置过期时间 一天
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24));
        String token = jwtBuilder.compact();
        return token;
    }


    public static Map<String,Object> parseToken(String token) {
        Jwt jwt = Jwts.parser().setSigningKey(SECRET).parse(token);
        Map<String,Object> claims = (Map<String, Object>) jwt.getBody();
        return claims;
    }



    public static void main(String[] args) {
        String token = JWTUtils.generateToken("admin");
        System.out.println(token);
        Map<String, Object> map = JWTUtils.parseToken(token);
        String acct = (String)map.get("acct");
        System.out.println(acct);
    }
}
