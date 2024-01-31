package com.ruoqing.dynastyForum.util;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

public class JWTUtil {

    private static final String SECRET = "qqwcby";

    public final static String USER_KEY = "userKey";

    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String getUserKey(String token) {
        Object o = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get(USER_KEY);
        return JSONUtil.toJsonStr(o);
    }

}
