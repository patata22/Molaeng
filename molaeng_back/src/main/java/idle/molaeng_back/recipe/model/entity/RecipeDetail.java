package idle.molaeng_back.recipe.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RecipeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_detail_id")
    private long recipeDetailId;

    @Column(name="recipe_order")
    private int recipeOrder;

    @Column(name="recipe_detail_img")
    private String recipeDetailImg;

    @Column(name="recipe_content")
    private String recipeContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Builder

    public RecipeDetail(long recipeDetailId, int recipeOrder, String recipeDetailImg, String recipeContent, Recipe recipe) {
        this.recipeDetailId = recipeDetailId;
        this.recipeOrder = recipeOrder;
        this.recipeDetailImg = recipeDetailImg;
        this.recipeContent = recipeContent;
        this.recipe = recipe;
    }
}
