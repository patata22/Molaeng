package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecipeMainSubIngredientRes {
    //RecipeController에서 주재료 리스트와 부재료 리스트를 모두 실어서 보내주려고 만듦.
    //근데 이게 맞는 건지는 잘 모르겠음..
    List<RecipeIngredientRes> recipeIngredientResList;
    List<RecipeSubIngredientRes> recipeSubIngredientResList;

    public RecipeMainSubIngredientRes(List<RecipeIngredientRes> recipeIngredientResList, List<RecipeSubIngredientRes> recipeSubIngredientResList) {
        this.recipeIngredientResList = recipeIngredientResList;
        this.recipeSubIngredientResList = recipeSubIngredientResList;
    }
}
