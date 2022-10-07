package idle.molaeng_back.diary.controller;

import idle.molaeng_back.diary.request.SaveDiaryReq;
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
    public ResponseEntity saveDiary(@RequestHeader Map<String,Object> header,@RequestBody SaveDiaryReq saveDiaryReq){
        long userId = Long.parseLong((String)header.get("userid"));
        long recipeId = saveDiaryReq.getRecipeId();
        int saveCost = saveDiaryReq.getSaveCost();

        Map<String, Object> resultMap = new HashMap<>();
        try{
            resultMap.put("diaryId", diaryService.saveDiary(userId, recipeId, saveCost));
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/calendar")
    public ResponseEntity getCalendar(@RequestHeader Map<String,Object> header, @RequestParam int year, @RequestParam int month){
        long userId = Long.parseLong((String)header.get("userid"));
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("saveCostList", diaryService.findDiaryByCalendar(userId, year, month));
            logger.info("resultMap: "+resultMap);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/week")
    public ResponseEntity getWeekGraph(@RequestHeader Map<String,Object> header, @RequestParam int year, @RequestParam int month){
        long userId = Long.parseLong((String)header.get("userid"));
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
    public ResponseEntity getMonthGraph(@RequestHeader Map<String,Object> header, @RequestParam int year, @RequestParam int month){
        long userId = Long.parseLong((String)header.get("userid"));
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
    public ResponseEntity getDiary(@RequestHeader Map<String,Object> header, String date){
        long userId = Long.parseLong((String)header.get("userid"));
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
