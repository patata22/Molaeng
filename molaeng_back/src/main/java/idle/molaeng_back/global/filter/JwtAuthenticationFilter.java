package idle.molaeng_back.global.filter;
import idle.molaeng_back.global.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessToken = jwtTokenUtil.resolveAccessToken((HttpServletRequest) request);

        if(accessToken != null) {
            // Access Token이 유효하다면 인증 정보를 저장한다.
            if(jwtTokenUtil.validateToken(accessToken)) {
                Authentication authentication = jwtTokenUtil.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 아니면 403 에러 발생
        }

        chain.doFilter(request, response);
    }
}