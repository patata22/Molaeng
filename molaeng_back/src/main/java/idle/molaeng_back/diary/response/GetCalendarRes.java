package idle.molaeng_back.diary.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class GetCalendarRes {
    private String mealDate;
    private int saveCost;

    public GetCalendarRes(String mealDate, int saveCost) {
        this.mealDate = mealDate;
        this.saveCost = saveCost;
    }
}
