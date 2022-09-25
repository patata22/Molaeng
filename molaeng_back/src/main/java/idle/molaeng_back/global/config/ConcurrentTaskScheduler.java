package idle.molaeng_back.global.config;

import idle.molaeng_back.global.api.NaverShoppingApi;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class ConcurrentTaskScheduler {
    // 자정마다 실행 (cron = "초 분 시 일 월 요일 년(생략가능)")

    private static final NaverShoppingApi naverShoppingApi = new NaverShoppingApi();
//    public ConcurrentTaskScheduler(){
//        naverShoppingApi.callApi("햅쌀");
//    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void ingredient_price_schedule(String query){
        naverShoppingApi.callApi(query);
    }
}
