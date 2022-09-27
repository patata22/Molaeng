package idle.molaeng_back.user.controller;

import idle.molaeng_back.user.model.UserProfileResponse;
import idle.molaeng_back.user.service.UserService;
import idle.molaeng_back.user.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserControlloer {

    private final UserServiceImpl userService;

//    @PostMapping("/login")
//    public ResponseEntity login(){
//        return new ResponseEntity<>(1, HttpStatus.OK);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity logout(){
//        return new ResponseEntity<>(1, HttpStatus.OK);
//    }

    @DeleteMapping
    public ResponseEntity deleteAccount(){
        HashMap<String, Object> result = new HashMap<>();


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="마이페이지 사용자 정보 조회", notes = "userId를 이용하여 닉네임, 거주지역 정보를 불러온다.")
    @PostMapping
    public ResponseEntity getProfile(@RequestParam long userId){
        HashMap<String, Object> result = new HashMap<>();

        try {
            UserProfileResponse resultRes = userService.getUserProfile(userId);
            result.put("result", resultRes);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (Exception e){
            result.put("message", "마이페이지 조회 오류");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    @ApiOperation(value="마이페이지 사용자 정보 수정", notes = "사용자의 닉네임, 거주지역 정보를 수정한다.")
    @PutMapping
    public ResponseEntity updateProfile(){
        HashMap<String, Object> result = new HashMap<>();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
