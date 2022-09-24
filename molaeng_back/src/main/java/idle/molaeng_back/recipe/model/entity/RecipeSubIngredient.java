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
    private long sub_ingredient_id;

    @Column(name="sub_ingredient_name")
    private String sub_ingredient_name;

    @Column(name="weight_unit")
    private String weight_unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeSubIngredient(long sub_ingredient_id, String sub_ingredient_name, String weight_unit, Recipe recipe) {
        this.sub_ingredient_id = sub_ingredient_id;
        this.sub_ingredient_name = sub_ingredient_name;
        this.weight_unit = weight_unit;
        this.recipe = recipe;
    }
}
