package idle.molaeng_back.recipe.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private double needWeight;

    @Column(name="weight_unit")
    private String weightUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    @JsonBackReference
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    @JsonBackReference
    private Ingredient ingredient;


    @Builder
    public RecipeIngredient(long recipeIngredientId, double needWeight, String weightUnit, Recipe recipe, Ingredient ingredient) {
        this.recipeIngredientId = recipeIngredientId;
        this.needWeight = needWeight;
        this.weightUnit = weightUnit;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }
}
