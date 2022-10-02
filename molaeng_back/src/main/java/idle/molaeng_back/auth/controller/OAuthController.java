package idle.molaeng_back.auth.controller;


import idle.molaeng_back.auth.model.TokenDto;
import idle.molaeng_back.auth.service.AuthService;
import idle.molaeng_back.auth.service.OAuthService;
import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private final OAuthService oAuthService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final AuthService authService;

    @GetMapping("/kakaoLogin")
    public ResponseEntity kakaoLogin(@RequestParam String code, HttpSession session) throws IOException{
        HashMap<String, Object> result = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();
        String kakaoToken;
        User user;

        try {
            kakaoToken = oAuthService.getAccessToken(code);
            userInfo = oAuthService.getUserInfo(kakaoToken);
            if(!userService.isMember(userInfo.get("id").toString())){
                user = userService.joinUser(userInfo.get("nickname").toString(), userInfo.get("id").toString());
            }else{
                user = userService.findByUuid(userInfo.get("id").toString());
            }

            System.out.println("#########" + user.getUserId() + " " + user.getNickname());

            log.info("????????????");
            System.out.println("???????????????");
            TokenDto.Refresh refresh = authService.createTokens(user);
            log.info("!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!");

            result.put("result", userInfo);
            result.put("message", "success");
            result.put("refreshToken", refresh);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (Exception e){
            result.put("result", code);
            result.put("message", "카카오 소셜 로그인 실패");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/kakaoLogout")
    public void kakaoLogout(){
        return;
    }
}
