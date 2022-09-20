package idle.molaeng_back.user.controller;

import idle.molaeng_back.user.service.UserService;
import idle.molaeng_back.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping
    public ResponseEntity getProfile(@RequestBody long userId){
        HashMap<String, Object> result = new HashMap<>();


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateProfile(){
        HashMap<String, Object> result = new HashMap<>();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
