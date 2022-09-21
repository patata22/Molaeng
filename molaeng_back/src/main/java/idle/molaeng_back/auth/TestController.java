package idle.molaeng_back.auth;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
@RestController
public class TestController {


//    @ResponseBody
    @PostMapping("/oauth2/code/kakao")
    public String kakaoCallback(@RequestParam String code) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        return "ok";
    }

}

///login/oauth2/code/kakao