package idle.molaeng_back.diary.service;

import idle.molaeng_back.diary.model.Diary;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DiaryService {

    long saveDiary(long userId, long recipeId, int saveCost);

    List<Diary> findDiaryByCalendar(long userId, int year, int month);
    List<Diary> findDiaryByWeek(long userId, int year, int month);
    List<Diary> findDiaryByMonth(long userId, int year, int month);
    List<Diary> findDiaryByDate(long userId, String date);

    long deleteDiaryByDiaryId(long diaryId);
}
