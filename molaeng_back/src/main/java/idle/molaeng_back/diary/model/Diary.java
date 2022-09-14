package idle.molaeng_back.diary.model;

import idle.molaeng_back.recipe.model.Recipe;
import idle.molaeng_back.user.model.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="diary_id")
    private long diaryId;

    @Column(name="meal_date")
    private LocalDate mealDate;

    @Column(name="save_cost")
    private int saveCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;
}
