package idle.molaeng_back.auth.service;

import idle.molaeng_back.auth.model.TokenDto;
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

    @Override
    public TokenDto.Refresh createTokens(User user) {

        // Token 발급부분에서 오류 발생
        // Redis에는 데이터 삽입 정상적으로 이루어지고 있는 것 확인


//        System.out.println("111111111111");
//        String accessToken = jwtTokenUtil.generateAccessToken();
//        System.out.println("2222222222222222");
//        String refreshToken = jwtTokenUtil.generateRefreshToken();
//        System.out.println("33333333333333333");
//
//        String userId = String.valueOf(user.getUserId());
//        redisUtil.setDataExpire(userId, refreshToken, JwtTokenUtil.REFRESH_TOKEN_VALIDITY);
//        System.out.println("444444444444444444444");
//
//        return TokenDto.Refresh.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();

        redisUtil.setDataExpire(String.valueOf(user.getUserId()), "1111", 10000);
        return TokenDto.Refresh.builder().build();
    }

    @Override
    public TokenDto.Refresh refreshTokens(String accessToken, String refreshToken) {
        String userId = jwtTokenUtil.getUserIdFromToken(accessToken);

        if(jwtTokenUtil.validateToken(refreshToken) && refreshToken.equals(redisUtil.getData(userId))) {
            User user = User.builder().userId(Long.parseLong(userId)).build();
            Claims claims = jwtTokenUtil.getClaims(user);
            String newAccessToken = jwtTokenUtil.generateAccessToken();
            String newRefreshToken = jwtTokenUtil.generateRefreshToken();
            redisUtil.setDataExpire(userId, newRefreshToken, JwtTokenUtil.REFRESH_TOKEN_VALIDITY);    // JwtTokenUtil과 똑같이 설정

            return TokenDto.Refresh.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
        } else {
            throw new AuthException.RefreshTokenExpired();
        }
    }

    @Override
    public TokenDto.Refresh deleteTokens(User user) {
        String accessToken = "";
        String refreshToken = "";

        String userId = String.valueOf(user.getUserId());
        redisUtil.deleteData(userId);

        return TokenDto.Refresh.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}