package idle.molaeng_back.recipe.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeSubIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sub_ingredient_id")
    private long subIngredientId;

    @Column(name="sub_ingredient_name")
    private String subIngredientName;

    @Column(name="weight_unit")
    private String weightUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeSubIngredient(long subIngredientId, String subIngredientName, String weightUnit, Recipe recipe) {
        this.subIngredientId = subIngredientId;
        this.subIngredientName = subIngredientName;
        this.weightUnit = weightUnit;
        this.recipe = recipe;
    }
}
