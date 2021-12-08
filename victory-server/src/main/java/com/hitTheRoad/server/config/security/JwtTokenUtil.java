package com.hitTheRoad.server.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* jwt工具类
* */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;


    /*
    * 根据用户生成token
    * */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }


    /*
    * 从token中获取用户名
    * */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    /*
    * 从token中获取载荷
    * */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /*
    * 根据荷载生成JWT
    * */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                   .setClaims(claims)
                    .setExpiration(generateExpressionDate())
                    .signWith(SignatureAlgorithm.HS512,secret)
                    .compact();
    }

    /*
    * 生成失效时间
    * */
    public Date generateExpressionDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /*
    * 判断是否有效
    * */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    /*
    * 判断是否失效
    * */
    public boolean isTokenExpired(String token) {

        Date expireDate = getExpireDateFromToken(token);
        return expireDate.before(new Date());
    }

    /*
    * 获取失效时间
    * */
    public Date getExpireDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }
    /*
    * 判断token是否可以被刷新
    * 如果过期就可以
    * */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }
    /*
    * 刷新token
    * */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }


}
