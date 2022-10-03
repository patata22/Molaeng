package idle.molaeng_back.global.util;

import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -8380158647791144627L;

    public static final long ACCESS_TOKEN_VALIDITY = 30 * 60L;              // 초 단위(=30분)
    public static final long REFRESH_TOKEN_VALIDITY = 3 * 24 * 60 * 60L;    // 초 단위(= 3일)

    @Value("${jwt.secret-key}")                   // application.properties 에 저장된 값
    private String secretKey;

    private final UserService userService;

    // Base64 Encoding
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Claims getClaims(User user) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getUserId()));
//        claims.put("isUser", user.getIsStudent());
        /* claim에 추가할 내용들 여기에 추가 */

        //예시로 넣어봄
//        claims.put("isUser", true);

        return claims;
    }

    // Token이 유효한지 검사한다.
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(ExpiredJwtException e) {
            return false;
        }
    }

    // Access Token을 생성한다.
    public String generateAccessToken(Claims claims) {
        log.info("generateAccessToken 함수");

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Refresh Token을 생성한다.
    public String generateRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Request의 Header에서 token을 가져온다.
    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader("Authorization");      // Header에 담을 이름
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("Refresh");
    }

    // Token에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        User user = userService.getUser(Integer.parseInt(this.getUserIdFromToken(token)));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    // Token에서 userId 추출
    public String getUserIdFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch(ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
}