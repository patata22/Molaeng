package idle.molaeng_back.auth.service;

import idle.molaeng_back.auth.model.JwtTokenDto;
import idle.molaeng_back.global.exception.AuthException;
import idle.molaeng_back.global.util.JwtTokenUtil;
import idle.molaeng_back.global.util.RedisUtil;
import idle.molaeng_back.user.model.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final RedisUtil redisUtil;


    // Jwt Token 생성
    @Override
    public JwtTokenDto createTokens(User user) {
        log.info("Token 생성하러 들어왔습니다.");
        String accessToken = jwtTokenUtil.generateAccessToken(jwtTokenUtil.getClaims(user));

        log.info("access 토큰은 발급 받았어요.");
        String refreshToken = jwtTokenUtil.generateRefreshToken();

        log.info("토큰 생성했어요" + accessToken + " " + refreshToken);
        String userId = String.valueOf(user.getUserId());
        redisUtil.setDataExpire(userId, refreshToken, JwtTokenUtil.REFRESH_TOKEN_VALIDITY);

        log.info("레디스 저장 했습니다.");

        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // Refresh token 발급 받는 메서드
    @Override
    public JwtTokenDto refreshTokens(String accessToken, String refreshToken) {
        String userId = jwtTokenUtil.getUserIdFromToken(accessToken);

        if(jwtTokenUtil.validateToken(refreshToken) && refreshToken.equals(redisUtil.getData(userId))) {
            User user = User.builder().userId(Integer.parseInt(userId)).build();
            Claims claims = jwtTokenUtil.getClaims(user);
            String newAccessToken = jwtTokenUtil.generateAccessToken(claims);
            String newRefreshToken = jwtTokenUtil.generateRefreshToken();
            redisUtil.setDataExpire(userId, newRefreshToken, JwtTokenUtil.REFRESH_TOKEN_VALIDITY);    // JwtTokenUtil과 똑같이 설정

            return JwtTokenDto.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
        } else {
            throw new AuthException.RefreshTokenExpired();
        }
    }


    // 발급받은 메서드 삭제하는 메서드
    @Override
    public JwtTokenDto deleteTokens(User user) {
        String accessToken = "";
        String refreshToken = "";

        String userId = String.valueOf(user.getUserId());
        redisUtil.deleteData(userId);

        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
