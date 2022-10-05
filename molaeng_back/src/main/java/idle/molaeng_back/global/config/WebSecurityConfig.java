package idle.molaeng_back.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String[] PERMIT_URL_ARRAY = {
//            /* swagger v2 */
//            "/v2/api-docs",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            "/",
//            "/index",
            "/**/favicon.ico",
//            "/editprofile/**"
    };

    @Override
    public void configure(WebSecurity web){ // 보안 예외처리(HTML, 정적리소스)
        web.ignoring()
                .antMatchers("/resources/**","/char/**", "/**/*.css", "/**/*.png", "/**/*.jpg",
                        "/**/*.gif","/**/*.ttf",
                        "/editprofile/**", "/user/**",
                        "/**/favicon.ico");
        web.ignoring().antMatchers(PERMIT_URL_ARRAY);
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 보안처리

        http.csrf().disable();

        // 일반적인 루트가 아닌 다른 방식으로 요청시 거절, header에 id, pw가 아닌 token(jwt)을 달고 간다.
        // 그래서 basic이 아닌 bearer를 사용한다.
        http.httpBasic().disable() // 요청에 대한 사용권한 체크
                .exceptionHandling()

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
//                .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
