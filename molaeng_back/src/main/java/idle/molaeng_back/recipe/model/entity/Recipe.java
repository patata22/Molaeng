package idle.molaeng_back.recipe.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.user.model.Outeat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @JsonBackReference
    private List<RecipeLike> recipeLikeList;

    @OneToMany(mappedBy = "recipe")
    @JsonManagedReference
    private List<Review> reviewList;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeDetail> recipeDetailList;

    @OneToMany(mappedBy = "recipe")
    private List<Diary> diaryList;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredientList;

    @Builder
    public Recipe(long recipeId, String recipeName, String recipeImage, int recipeKcal, int serving, int oneScore, int twoScore, int threeScore, int fourScore, int fiveScore, List<Outeat> outeatList, List<RecipeLike> recipeLikeList, List<Review> reviewList, List<RecipeDetail> recipeDetailList, List<Diary> diaryList, List<RecipeIngredient> recipeIngredientList) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.recipeKcal = recipeKcal;
        this.serving = serving;
        this.oneScore = oneScore;
        this.twoScore = twoScore;
        this.threeScore = threeScore;
        this.fourScore = fourScore;
        this.fiveScore = fiveScore;
        this.outeatList = outeatList;
        this.recipeLikeList = recipeLikeList;
        this.reviewList = reviewList;
        this.recipeDetailList = recipeDetailList;
        this.diaryList = diaryList;
        this.recipeIngredientList = recipeIngredientList;
    }

    public void setOneScore(int oneScore) {
        this.oneScore = oneScore;
    }

    public void setTwoScore(int twoScore) {
        this.twoScore = twoScore;
    }

    public void setThreeScore(int threeScore) {
        this.threeScore = threeScore;
    }

    public void setFourScore(int fourScore) {
        this.fourScore = fourScore;
    }

    public void setFiveScore(int fiveScore) {
        this.fiveScore = fiveScore;
    }
}
