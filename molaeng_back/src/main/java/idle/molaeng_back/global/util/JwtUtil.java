//package idle.molaeng_back.global.util;
//
//import idle.molaeng_back.user.model.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    public static final long ACCESS_TOKEN_VALIDITY = 30 * 60L;              // 초 단위(=30분)
//    public static final long REFRESH_TOKEN_VALIDITY = 3 * 24 * 60 * 60L;    // 초 단위(= 3일)
//
//    public String generateJwtToken(long uuid, String nickname, String profileImg) {
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + 3 * 60 * 60 * 1000L);
//
//        Map<String, Object> headers = headersSetting();
//        Map<String, Object> payloads = new HashMap<>();
//        payloads.put("uuid", uuid);
//        payloads.put("nickname", nickname);
//        payloads.put("profileImg", profileImg);
//
//        return Jwts.builder()
//                .setHeader(headers)
//                .setClaims(payloads)
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // 알고리즘, 시크릿 키
//                .compact();
//    }
//
//    public String generateJwtToken(User user) {
//        return generateJwtToken(user.getUuid(), user.getNickname(), user.getProfileImg());
//    }
//
//    public String generateRefreshToken(long uuid){
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + 14 * 24 * 60 * 60 * 1000L);
//
//        Map<String, Object> headers = headersSetting();
//        Map<String, Object> payloads = new HashMap<>();
//        payloads.put("uuid", uuid);
//
//        return Jwts.builder()
//                .setHeader(headers)
//                .setClaims(payloads)
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
//                .compact();
//    }
//
//    public Map<String, Object> headersSetting(){
//        Map<String, Object> headers = new HashMap<>();
//        headers.put("typ", "JWT");
//        headers.put("alg", "HS256");
//        return headers;
//    }
//
//    // 백에서 토큰 복호화할 때 사용하는 메서드
//    public Claims parseJwtToken(String token) {
//        Claims claims = null;
//        token = token.split(" ")[1];
//        try{
//            claims = Jwts.parser()
//                    .setSigningKey(secretKey.getBytes("UTF-8"))
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch(Exception e){
//            System.out.println(e);
//        }
//        return claims;
//    }
//
//    // JwtToken 유효성 검사하는 메서드
//    public HttpStatus validateJwtToken(String authToken){
//        try{
//            authToken = authToken.split(" ")[1];
//            log.info("토큰 : {}", authToken);
//            Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(authToken);
//            return HttpStatus.OK;
//        }catch (ExpiredJwtException e){
//            return HttpStatus.UNAUTHORIZED;
//        }catch (Exception e){
//            return HttpStatus.BAD_REQUEST;
//        }
//    }
//}
