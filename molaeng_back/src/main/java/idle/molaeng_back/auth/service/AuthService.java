package idle.molaeng_back.auth.service;

import idle.molaeng_back.auth.model.TokenDto;
import idle.molaeng_back.user.model.User;

public interface AuthService {
    TokenDto.Refresh createTokens(User user);
    TokenDto.Refresh refreshTokens(String accessToken, String refreshToken);
    TokenDto.Refresh deleteTokens(User user);
}
