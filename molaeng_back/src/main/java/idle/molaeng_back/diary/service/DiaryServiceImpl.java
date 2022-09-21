package idle.molaeng_back.diary.service;

import idle.molaeng_back.diary.controller.DiaryController;
import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.diary.model.DiaryRepository;
import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.repository.repository;
import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.model.userRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService{

    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    private DiaryRepository diaryRepository;

    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

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
        LocalDate startDate = null;
        if(month<10){
            startDate = LocalDate.parse(year+"-0"+month+"-"+"01"); //해당 월의 1일
        }else{
            startDate = LocalDate.parse(year+"-"+month+"-"+"01"); //해당 월의 1일
        }
        YearMonth targetYearMonth = YearMonth.from(startDate); //타겟 년월
        LocalDate endDate = targetYearMonth.atEndOfMonth(); //해당 월의 마지막 날

        return diaryRepository.findByUserUserIdAndMealDateBetween(userId, startDate, endDate);
    }

    @Override
    public int[] findDiaryByWeek(long userId, int year, int month) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        diaryRepository.findByUserUserIdAndMealDateBetween(userId, startDate, endDate);
        int[] saveCostList = new int[5];
        return saveCostList;
    }

    @Override
    public int[] findDiaryByMonth(long userId, int year, int month) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        diaryRepository.findByUserUserIdAndMealDateBetween(userId, startDate, endDate);
        int[] saveCostList = new int[6];
        return saveCostList;
    }

    @Override
    public List<Diary> findDiaryByDate(long userId, String date) {
        LocalDate mealDate = LocalDate.parse(date);

        return diaryRepository.findByUserUserIdAndMealDate(userId, mealDate);
    }

    @Override
    public long deleteDiaryByDiaryId(long diaryId) {
        diaryRepository.deleteByDiaryId(diaryId);

        return diaryId;
    }
}
