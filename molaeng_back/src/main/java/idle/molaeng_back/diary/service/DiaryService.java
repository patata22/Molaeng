package idle.molaeng_back.diary.service;

import idle.molaeng_back.diary.model.Diary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface DiaryService {

    public void saveDiary(long userId, long recipeId);

    public List<Diary> findDiaryByCalendar(long userId, int year, int month);
    public List<Diary> findDiaryByWeek(long userId, int year, int month);
    public List<Diary> findDiaryByMonth(long userId, int year, int month);
    public List<Diary> findDiaryByDate(long userId, String date);

    public void deleteDiaryByDiaryId(long diaryId);
}
