package idle.molaeng_back.diary.service;

import idle.molaeng_back.diary.response.GetCalendarRes;
import idle.molaeng_back.diary.response.GetDiaryRes;

import java.util.List;

public interface DiaryService {

    long saveDiary(long userId, long recipeId, int saveCost);

    List<GetCalendarRes> findDiaryByCalendar(long userId, int year, int month);
    int[] findDiaryByWeek(long userId, int year, int month);
    int[] findDiaryByMonth(long userId, int year, int month);
    List<GetDiaryRes> findDiaryByDate(long userId, String date);

    long deleteDiaryByDiaryId(long diaryId);
}
