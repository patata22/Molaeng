package idle.molaeng_back.diary.service;

import idle.molaeng_back.diary.controller.DiaryController;
import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.diary.model.DiaryRepository;
import idle.molaeng_back.diary.response.GetCalendarRes;
import idle.molaeng_back.diary.response.GetDiaryRes;
import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService{

    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    private DiaryRepository diaryRepository;
    private RecipeRepository recipeRepository;
    private UserRepository userRepository;

    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public long saveDiary(long userId, long recipeId, int saveCost) {
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);
        User user = userRepository.findByUserId(userId);
        Diary diary = Diary.builder().mealDate(LocalDate.now()).saveCost(saveCost).recipe(recipe).user(user).build();

        diaryRepository.save(diary);

        return diary.getDiaryId();
    }

    @Override
    public List<GetCalendarRes> findDiaryByCalendar(long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year,month,1);
        YearMonth targetYearMonth = YearMonth.from(startDate); //타겟 년월
        LocalDate endDate = targetYearMonth.atEndOfMonth(); //해당 월의 마지막 날

        List<Object[]> diaryList = diaryRepository.findSaveCostForCalendar(userId, startDate, endDate);

        List<GetCalendarRes> getCalendarResList = new ArrayList<>();

            diaryList.stream().forEach(objects -> {
                StringBuffer buffer1 = new StringBuffer();
                StringBuffer buffer2 = new StringBuffer();


                buffer1.append(objects[0]);
                buffer2.append(objects[1]);

                GetCalendarRes getCalendarRes = GetCalendarRes.builder()
                    .mealDate(buffer1.toString())
                    .saveCost(Integer.parseInt(buffer2.toString()))
                    .build();

                getCalendarResList.add(getCalendarRes);
            });

        return getCalendarResList;
    }

    @Override
    public int[] findDiaryByWeek(long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year,month,1);
        YearMonth targetYearMonth = YearMonth.from(startDate); //타겟 년월
        LocalDate endDate = targetYearMonth.atEndOfMonth(); //해당 월의 마지막 날

        LocalDate tmpDate = null; //각 주의 마지막 날
        int startDay = startDate.getDayOfWeek().getValue(); //첫째날 요일
        if(startDay>6){
            tmpDate = startDate.plusDays(6); //시작이 일요일
        }else{
            tmpDate = startDate.plusDays(6-startDay); //시작이 월~토
        }

        int startWeek = startDate.get(WeekFields.ISO.weekOfYear());
        int endWeek = endDate.get(WeekFields.ISO.weekOfYear());
        int weekCnt = endWeek-startWeek+1; //해당 월의 주차 수

        int[] saveCostList = new int[weekCnt];
        for(int i=0; i<weekCnt; i++){
            if(tmpDate.isAfter(endDate)){
                saveCostList[i] = diaryRepository.findSaveCostForGraph(userId, startDate, endDate);
            }else {
                saveCostList[i] = diaryRepository.findSaveCostForGraph(userId, startDate, tmpDate);
                startDate = tmpDate.plusDays(1);
                tmpDate = tmpDate.plusDays(7);
            }
        }
        return saveCostList;
    }

    @Override
    public int[] findDiaryByMonth(long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year,month,1);
        LocalDate endDate = null;

        int[] saveCostList = new int[6];
        for(int i=0; i<6; i++){
            YearMonth targetYearMonth = YearMonth.from(startDate); //타겟 년월
            endDate = targetYearMonth.atEndOfMonth(); //해당 월의 마지막 날
            saveCostList[i] = diaryRepository.findSaveCostForGraph(userId, startDate, endDate);
            startDate = startDate.minusMonths(1);
        }

        return saveCostList;
    }

    @Override
    public List<GetDiaryRes> findDiaryByDate(long userId, String date) {
        LocalDate mealDate = LocalDate.parse(date);
        List<Diary> diaryList = diaryRepository.findByUserUserIdAndMealDate(userId, mealDate);
        List<GetDiaryRes> getDiaryResList = new ArrayList<>();
        for(Diary diary : diaryList){
            GetDiaryRes getDiaryRes = GetDiaryRes.builder()
                    .diaryId(diary.getDiaryId())
                    .recipeId(diary.getRecipe().getRecipeId())
                    .recipeName(diary.getRecipe().getRecipeName())
                    .saveCost(diary.getSaveCost())
                    .build();

            getDiaryResList.add(getDiaryRes);
        }

        return getDiaryResList;
    }

    @Override
    public long deleteDiaryByDiaryId(long diaryId) {
        diaryRepository.deleteByDiaryId(diaryId);

        return diaryId;
    }
}
