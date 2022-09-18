package idle.molaeng_back.diary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DiaryController {

    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    @PostMapping("/diary")
    public ResponseEntity<Map<String,Object>> saveDiary(@RequestBody long userId, long recipeId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/diary/calendar")
    public ResponseEntity<Map<String,Object>> getCalendar(@RequestBody long userId, int year, int month){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/diary/week")
    public ResponseEntity<Map<String,Object>> getWeekGraph(@RequestBody long userId, int year, int month){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/diary/month")
    public ResponseEntity<Map<String,Object>> getMonthGraph(@RequestBody long userId, int year, int month){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/diary")
    public ResponseEntity<Map<String,Object>> getDiary(@RequestBody long userId, String date){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/diary")
    public ResponseEntity<Map<String,Object>> deleteDiary(@RequestBody long diaryId){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
