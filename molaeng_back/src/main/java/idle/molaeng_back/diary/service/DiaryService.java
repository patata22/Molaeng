package idle.molaeng_back.diary.service;

import idle.molaeng_back.diary.model.Diary;

import java.util.List;

public interface DiaryService {

    long saveDiary(long userId, long recipeId, int saveCost);

    List<Diary> findDiaryByCalendar(long userId, int year, int month);
    int[] findDiaryByWeek(long userId, int year, int month);
    int[] findDiaryByMonth(long userId, int year, int month);
    List<Diary> findDiaryByDate(long userId, String date);

    long deleteDiaryByDiaryId(long diaryId);
}
