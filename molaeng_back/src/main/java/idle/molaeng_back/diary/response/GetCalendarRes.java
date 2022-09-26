package idle.molaeng_back.diary.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class GetCalendarRes {
    private LocalDate mealDate;
    private int saveCost;

    public GetCalendarRes(LocalDate mealDate, int saveCost) {
        this.mealDate = mealDate;
        this.saveCost = saveCost;
    }
}
