package idle.molaeng_back.recipe.model.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    private long ingredientId;

    @Column(name="ingredient_name")
    private String ingredientName;

    @Column(name="ingredient_price")
    private int ingredientPrice;

    @Column(name="ingredient_weight")
    private int ingredientWeight;

    @Column(name="ingredient_unit")
    private String ingredientUnit;

    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipeIngredientList;

    @Builder
    public Ingredient(long ingredientId, String ingredientName,int ingredientPrice, String ingredientUnit, List<RecipeIngredient> recipeIngredientList) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.ingredientUnit = ingredientUnit;
        this.recipeIngredientList = recipeIngredientList;
    }
}
