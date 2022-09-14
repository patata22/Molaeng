package idle.molaeng_back.recipe.model.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
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
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
