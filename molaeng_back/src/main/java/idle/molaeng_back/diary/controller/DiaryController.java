package idle.molaeng_back.diary.controller;

import idle.molaeng_back.diary.service.DiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity saveDiary(@RequestBody long userId, @RequestBody long recipeId, @RequestBody int saveCost){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            resultMap.put("diaryId", diaryService.saveDiary(userId, recipeId, saveCost));
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/calendar")
    public ResponseEntity getCalendar(@RequestParam long userId, @RequestParam int year, @RequestParam int month){
        logger.info("userId:"+userId+" year:"+year+" month:"+month);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("saveCostList", diaryService.findDiaryByCalendar(userId, year, month));
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/week")
    public ResponseEntity getWeekGraph(@RequestParam long userId, @RequestParam int year, @RequestParam int month){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("year",year);
            resultMap.put("month",month);
            resultMap.put("saveCostList", diaryService.findDiaryByWeek(userId, year, month));
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/month")
    public ResponseEntity getMonthGraph(@RequestParam long userId, @RequestParam int year, @RequestParam int month){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("year",year);
            resultMap.put("month",month);
            resultMap.put("saveCostList", diaryService.findDiaryByMonth(userId, year, month));
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getDiary(@RequestParam Long userId, String date){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("mealDate",date);
            resultMap.put("mealList", diaryService.findDiaryByDate(userId, date));
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity deleteDiary(@PathVariable long diaryId){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("diaryId", diaryService.deleteDiaryByDiaryId(diaryId));
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
