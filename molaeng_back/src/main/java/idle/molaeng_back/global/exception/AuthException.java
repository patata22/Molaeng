package idle.molaeng_back.global.exception;

public class AuthException {

    public static class RefreshTokenExpired extends RuntimeException {
        public RefreshTokenExpired() { super("Refresh Token이 만료되었습니다."); }
    }
}