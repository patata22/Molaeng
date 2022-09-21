package idle.molaeng_back.global;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;
    @GetMapping("/test")
    public String test(){
        return "Hello redis!";
    }
    @PostMapping("/redisTest")
    public ResponseEntity addRedisKey(@RequestBody String key){
        // vop 에 다른 타입이 들어갈 수 있는지 확인할 필요 있음
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(key, "2");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/redisTest")
    public ResponseEntity getRedisValue(@RequestBody String key){
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String value = vop.get(key);
        return new ResponseEntity(value, HttpStatus.OK);
    }
}
