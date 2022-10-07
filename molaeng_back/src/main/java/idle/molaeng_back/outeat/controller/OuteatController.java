package idle.molaeng_back.outeat.controller;

import idle.molaeng_back.outeat.service.OuteatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/recipe/outeat")
@RequiredArgsConstructor
public class OuteatController {

    private final OuteatService outeatService;


    @GetMapping("/{recipeId}")
    public ResponseEntity outeatPrice(@RequestHeader Map<String,Object> header, @PathVariable long recipeId){
        long userId = Long.parseLong((String)header.get("userid"));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("seoul",outeatService.outeatSeoul(recipeId));
        try{
            resultMap.putAll(outeatService.outeatGugun(recipeId, userId));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return new ResponseEntity(resultMap, HttpStatus.OK);
    }
}
