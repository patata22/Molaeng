package idle.molaeng_back.auth.service;

import idle.molaeng_back.auth.model.JwtTokenDto;
import idle.molaeng_back.user.model.User;

public interface AuthService {
    JwtTokenDto createTokens(User user);
    JwtTokenDto refreshTokens(String accessToken, String refreshToken);
    JwtTokenDto deleteTokens(User user);
}
