package idle.molaeng_back.diary.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Diary save(Diary diary);

    List<Diary> findByUserUserIdAndMealDateBetween(long userId, LocalDate startDate, LocalDate endDate);
    List<Diary> findByUserUserIdAndMealDate(long userId, LocalDate mealDate);

    void deleteByDiaryId(long diaryId);
}
