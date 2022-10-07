package idle.molaeng_back.recipe.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
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
    private double ingredientPrice;

    @Column(name="ingredient_weight")
    private int ingredientWeight;

    @Column(name="ingredient_unit")
    private String ingredientUnit;

    @Column(name="ingredient_block_word")
    private String ingredientBlockWord;

    @Column(name="ingredient_iscrawl")
    private int ingredientIsCrawl;

    @OneToMany(mappedBy = "ingredient")
    @BatchSize(size=100)
    @JsonManagedReference
    private List<RecipeIngredient> recipeIngredientList;

    @Builder
    public Ingredient(long ingredientId, String ingredientName,double ingredientPrice, int ingredientWeight, String ingredientUnit, String ingredientBlockWord, int ingredientIsCrawl, List<RecipeIngredient> recipeIngredientList) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.ingredientWeight = ingredientWeight;
        this.ingredientUnit = ingredientUnit;
        this.ingredientBlockWord = ingredientBlockWord;
        this.ingredientIsCrawl = ingredientIsCrawl;
        this.recipeIngredientList = recipeIngredientList;
    }

    public void updatePrice(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public int pricePerWeight(double needWeight, String needUnit) {
        // ip: g 또는 개당 재료 가격
        double ip=this.ingredientPrice;
        // nw: 레시피를 만드는데 필요한 재료 무게/단위 (ex: 2개, 30g 등)
        double nw=needWeight;


        if(this.getIngredientUnit().equals(needUnit) || needUnit.equals("ml") || this.getIngredientUnit().equals("ml")){
            return (int) Math.round(ip*nw);
        } else {
            return (int) Math.round(ip*this.ingredientWeight*nw);
        }

    }
}
