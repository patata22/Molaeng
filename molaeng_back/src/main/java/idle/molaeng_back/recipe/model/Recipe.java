package idle.molaeng_back.recipe.model;

import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.user.model.Outeat;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_id")
    private long recipeId;

    @Column(name="recipe_name")
    private String recipeName;

    @Column(name="recipe_image")
    private String recipeImage;

    @Column(name="recipe_kcal")
    private int recipeKcal;

    @Column(name="serving")
    private int serving;

    @Column(name="one_score")
    private int oneScore;

    @Column(name="two_score")
    private int twoScore;

    @Column(name="three_score")
    private int threeScore;

    @Column(name="four_score")
    private int fourScore;

    @Column(name="five_score")
    private int fiveScore;

    @OneToMany(mappedBy = "recipe")
    private List<Outeat> outeatList;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeLike> recipeLikeList;

    @OneToMany(mappedBy = "recipe")
    private List<Review> reviewList;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeDetail> recipeDetailList;

    @OneToMany(mappedBy = "recipe")
    private List<Diary> diaryList;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredientList;

}
