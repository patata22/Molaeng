package idle.molaeng_back.diary.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Diary save(Diary diary);

    @Query(value = "select mealDate, sum(saveCost) from Diary where user.userId=:userId and mealDate between :startDate and :endDate group by mealDate")
    List<Object[]> findSaveCostForCalendar(long userId, LocalDate startDate, LocalDate endDate);

    @Query(value = "select coalesce(sum(saveCost),0) from Diary where user.userId=:userId and mealDate between :startDate and :endDate")
    int findSaveCostForGraph(long userId, LocalDate startDate, LocalDate endDate);

    List<Diary> findByUserUserIdAndMealDate(long userId, LocalDate mealDate);

    void deleteByDiaryId(long diaryId);
}
