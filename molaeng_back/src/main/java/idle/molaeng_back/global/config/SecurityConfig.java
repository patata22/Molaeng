//package idle.molaeng_back.global.config;
//
//import idle.molaeng_back.auth.CustomOAuth2UserService;
//import idle.molaeng_back.auth.JwtAuthFilter;
//import idle.molaeng_back.auth.OAuth2SuccessHandler;
//import idle.molaeng_back.auth.TokenService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final CustomOAuth2UserService oAuth2UserService;
//    private final OAuth2SuccessHandler successHandler;
//    private final TokenService tokenService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/token/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new JwtAuthFilter(tokenService),
//                        UsernamePasswordAuthenticationFilter.class)
//                // oauth2Login() : OAuth2Login 설정 부분
//                // loginPage() : login 페이지 url을 직접 설정
//                .oauth2Login()
////                .loginPage("/aaaa")
//                // successHandler() : 로그인 성공시
//                .successHandler(successHandler)
//                // userInfoEndpoint() : OAuth2 로그인 성공 후 설정
//                // userService() : oAuth2UserService에서 처리한다는 의미
//                .userInfoEndpoint().userService(oAuth2UserService);
//
//        http.addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
//    }
//}
//
//
//
