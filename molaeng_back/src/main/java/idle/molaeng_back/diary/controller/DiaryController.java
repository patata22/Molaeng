package idle.molaeng_back.diary.controller;

import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.diary.service.DiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    private DiaryService diaryService;

    @PostMapping
    public ResponseEntity saveDiary(@RequestBody long userId, @RequestBody long recipeId, @RequestBody int saveCost){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", diaryService.saveDiary(userId, recipeId, saveCost));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/calendar")
    public ResponseEntity getCalendar(@RequestBody long userId, @RequestBody int year, @RequestBody int month){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", diaryService.findDiaryByCalendar(userId, year, month));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity getWeekGraph(@RequestBody long userId, @RequestBody int year, @RequestBody int month){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", diaryService.findDiaryByWeek(userId, year, month));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/month")
    public ResponseEntity getMonthGraph(@RequestBody long userId, @RequestBody int year, @RequestBody int month){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", diaryService.findDiaryByMonth(userId, year, month));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getDiary(@RequestBody long userId, @RequestBody String date){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", diaryService.findDiaryByDate(userId, date));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteDiary(@RequestBody long diaryId){
        Map<String, Object> resultMap = new HashMap<>();
        logger.info("diarycontroller - diaryId : "+diaryId);
        resultMap.put("result", diaryService.deleteDiaryByDiaryId(diaryId));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
