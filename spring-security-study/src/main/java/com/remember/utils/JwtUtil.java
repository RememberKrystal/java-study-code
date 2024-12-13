package com.remember.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * JWT Token工具类，用于生成和解析JWT Token
 */
public class JwtUtil {
    /**
     * 过期时间(单位:秒)
     */
    public static final int ACCESS_EXPIRE = 60 * 60 * 60; // 60 小时

    /**
     * 加密算法
     */
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

    /**
     * 私钥 / 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取。
     * 切记：秘钥不能外露，在任何场景都不应该流露出去。
     * 应该大于等于 256位(长度32及以上的字符串)，并且是随机的字符串
     */
    public final static String SECRET = "Remember: The key should not be exposed and should not be disclosed in any scenario";

    /**
     * 秘钥实例
     */
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    /**
     * jwt签发者
     */
    private final static String JWT_ISS = "Remember";

    /**
     * jwt主题
     */
    private final static String SUBJECT = "Peripherals";

    /**
     * 生成访问令牌
     *
     * @param userId 用户名
     * @return 访问令牌
     */
    public static String genAccessToken(int userId) {
        // 生成令牌ID
        String uuid = UUID.randomUUID().toString();
        // 设置过期时间
        Date expireDate = Date.from(Instant.now().plusSeconds(ACCESS_EXPIRE));

        return Jwts.builder()
                // 设置头部信息
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                // 设置自定义负载信息
                .claim("userId", userId)
                .id(uuid) // 令牌ID
                .expiration(expireDate) // 过期日期
                .issuedAt(new Date()) // 签发时间
                .subject(SUBJECT) // 主题
                .issuer(JWT_ISS) // 签发者
                .signWith(KEY, ALGORITHM) // 签名
                .compact();
    }



    /**
     * 获取payload中的用户信息
     *
     * @param token JWT Token
     * @return 用户信息
     */
    public static int getUserFromToken(String token) {
        int userId = -1;
        Claims claims = parseClaims(token);
        if (claims != null) {
            userId = (int)claims.get("userId");
        }
        return userId;
    }

    /**
     * 获取JWT令牌的过期时间
     *
     * @param token JWT令牌
     * @return 过期时间的毫秒级时间戳
     */
    public static long getExpirationTime(String token) {

        Claims claims = parseClaims(token);
        if (claims != null) {
            return claims.getExpiration().getTime();
        }
        return 0L;
    }
    /**
     * 解析token
     *
     * @param token token
     * @return Jws<Claims>
     */
    public static Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }

    /**
     * 解析token的头部信息
     *
     * @param token token
     * @return token的头部信息
     */
    public static JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }

    /**
     * 解析token的载荷信息
     *
     * @param token token
     * @return token的载荷信息
     */
    public static Claims parsePayload(String token) {
        return parseClaim(token).getPayload();
    }


    /**
     * 解析JWT Token中的Claims
     *
     * @param token JWT Token
     * @return Claims
     */
    public static Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String token = genAccessToken(1);
        System.out.println(token);
        System.out.println(getUserFromToken(token));
    }
}

