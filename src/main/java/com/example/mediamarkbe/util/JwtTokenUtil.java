package com.example.mediamarkbe.util;


import com.example.mediamarkbe.security.UserPrincipal;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    private static final String SECRET_KEY = "secret";

    public static String generateAccessToken(UserPrincipal userPrincipal) {
        Map<String, Object> roles = new HashMap<>();
        roles.put("roles",userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(roles)
                .setSubject(String.format("%s,%s", userPrincipal.getId(), userPrincipal.getUsername()))
                .setIssuer("token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS384

                        , SECRET_KEY)
                .compact();
    }

    public static boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            throw  ex;
        } catch (IllegalArgumentException ex) {

        } catch (MalformedJwtException ex) {

        } catch (UnsupportedJwtException ex) {

        } catch (SignatureException ex) {

        }
        return false;
    }

    public static String getSubject(String accessToken) {
        return parseClaims(accessToken).getSubject();
    }

    public static Object getRoles(String accessToken){
        return parseClaims(accessToken).get("roles");
    }

    public static Claims parseClaims(String accessToken) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody();
    }
}
