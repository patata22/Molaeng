package idle.molaeng_back.user.controller;

import idle.molaeng_back.user.model.DTO.*;
import idle.molaeng_back.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원 탈퇴", notes = "사용자가 작성한 리뷰, 좋아요와 같은 정보를 더미 사용자에게 넘기고, 해당 계정의 정보를 삭제한다.")
    @DeleteMapping
    public ResponseEntity deleteAccount(@RequestParam long userId) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            userService.deleteUserAccount(userId);
            result.put("result", userId);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("result", userId);
            result.put("message", "회원 탈퇴 오류");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "사용자 정보 조회", notes = "userId를 이용하여 닉네임, 거주지역 정보를 불러온다.")
    @PostMapping
    public ResponseEntity getProfile(@RequestBody UserIdDTO userId) {
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(userId.getUserId());
        try {
            UserProfileResponse resultRes = userService.getUserProfile(userId.getUserId());
            result.put("result", resultRes);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", userId);
            result.put("message", "사용자 정보 조회 오류");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "마이페이지 사용자 정보 수정", notes = "사용자의 닉네임, 거주지역 정보를 수정한다.")
    @PutMapping
    public ResponseEntity updateProfile(@RequestBody UserProfileRequest userProfileRequest) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            userService.updateUserProfile(userProfileRequest);
            result.put("result", userProfileRequest.getUserId());
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            result.put("result", userProfileRequest.getUserId());
            result.put("message", "마이페이지 사용자 정보 수정 오류");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "로그인", notes = "Kakao uuid를 이용해 회원가입 여부를 판별하고, 로그인 혹은 가입을 진행")
    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody LoginReqDTO loginReqDTO) {
        long uuid = loginReqDTO.getUuid();
        String nickname = loginReqDTO.getNickname();
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            LoginResDTO userInfo = userService.Login(uuid, nickname);
            resultMap.put("userId", userInfo.getUserId());
            resultMap.put("nickname", userInfo.getNickname());
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "로그인에서 에러 발생");
            e.printStackTrace();
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
}
