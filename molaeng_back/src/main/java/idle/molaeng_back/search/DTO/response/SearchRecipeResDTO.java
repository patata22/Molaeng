package idle.molaeng_back.search.DTO.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchRecipeResDTO {
    boolean hasNext;
    List<RecipeResDTO> recipeList;

    @Builder
    public SearchRecipeResDTO(boolean hasNext, List<RecipeResDTO> recipeList) {
        this.hasNext = hasNext;
        this.recipeList = recipeList;
    }
}
