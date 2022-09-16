package idle.molaeng_back.recipe.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_ingredient_id")
    private long recipeIngredientId;

    @Column(name="need_weight")
    private int needWeight;

    @Column(name="weight_unit")
    private String weightUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;


    @Builder
    public RecipeIngredient(long recipeIngredientId, int needWeight, String weightUnit, Recipe recipe, Ingredient ingredient) {
        this.recipeIngredientId = recipeIngredientId;
        this.needWeight = needWeight;
        this.weightUnit = weightUnit;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }
}
