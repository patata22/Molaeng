package idle.molaeng_back.diary.service;

import idle.molaeng_back.diary.controller.DiaryController;
import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.diary.model.DiaryRepository;
import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class DiaryServiceImpl implements DiaryService{

    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    private DiaryRepository diaryRepository;

    @Override
    public long saveDiary(long userId, long recipeId, int saveCost) {
        Recipe recipe = null;
        User user = null;
        Diary diary = Diary.builder().mealDate(LocalDate.now()).saveCost(saveCost).recipe(recipe).user(user).build();

        diaryRepository.save(diary);

        return diary.getDiaryId();
    }

    @Override
    public List<Diary> findDiaryByCalendar(long userId, int year, int month) {
        LocalDate startDate = LocalDate.parse(year+"-"+month+"-"+01); //해당 월의 1일
        YearMonth targetYearMonth = YearMonth.from(startDate); //타겟 년월
        LocalDate endDate = targetYearMonth.atEndOfMonth(); //해당 월의 마지막 날

        return diaryRepository.findByUserUserIdAndMealDateBetween(userId, startDate, endDate);
    }

    @Override
    public List<Diary> findDiaryByWeek(long userId, int year, int month) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        return diaryRepository.findByUserUserIdAndMealDateBetween(userId, startDate, endDate);
    }

    @Override
    public List<Diary> findDiaryByMonth(long userId, int year, int month) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        return diaryRepository.findByUserUserIdAndMealDateBetween(userId, startDate, endDate);
    }

    @Override
    public List<Diary> findDiaryByDate(long userId, String date) {
        LocalDate mealDate = LocalDate.parse(date);
        logger.info("findDiaryByDate - mealDate : "+mealDate);
        return diaryRepository.findByUserUserIdAndMealDate(userId, mealDate);
    }

    @Override
    public long deleteDiaryByDiaryId(long diaryId) {
        diaryRepository.deleteByDiaryId(diaryId);

        return diaryId;
    }
}
