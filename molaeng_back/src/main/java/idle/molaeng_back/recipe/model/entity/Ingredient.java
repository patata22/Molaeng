package idle.molaeng_back.recipe.model.entity;

import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    private long ingredientId;

    @Column(name="ingredient_name")
    private String ingredientName;

    //지금 DB에 null이 들어가있어서 테스트용으로 임시 수정
    @Column(name="ingredient_price")
    private Integer ingredientPrice;
    //지금 DB에 null이 들어가있어서 테스트용으로 임시 수정
    @Column(name="ingredient_weight")
    private Integer ingredientWeight;

    @Column(name="ingredient_unit")
    private String ingredientUnit;

    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipeIngredientList;

    @Builder
    public Ingredient(long ingredientId, String ingredientName,int ingredientPrice, int ingredientWeight, String ingredientUnit, List<RecipeIngredient> recipeIngredientList) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.ingredientWeight = ingredientWeight;
        this.ingredientUnit = ingredientUnit;
        this.recipeIngredientList = recipeIngredientList;
    }

    public int pricePerWeight(double needWeight, String needUnit) {
        int ip=this.ingredientPrice;
        int iw=this.ingredientWeight;
        double nw=needWeight;

        // 200g 당근이 3500원인데 1kg이 필요할 때
        if(needUnit.equals("kg") && ingredientUnit.equals("g")){
            nw*=1000;
            // 쌀 1kg이 25000원인데 100g이 필요할 때
        } else if (needUnit.equals("g") && ingredientUnit.equals("kg")){
            ip/=1000;
        }

        // ml는 어떻게 할건지 차후 추가 필요~

        return (int)Math.round((ip/iw) * nw);
    }
}
