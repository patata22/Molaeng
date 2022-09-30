package idle.molaeng_back.search.DTO.request;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchRecipeDTO {
    int page;
    int size;
    List<SearchRecipeByIngredientReqDTO> ingredientList;

    public SearchRecipeDTO(int page, int size, List<SearchRecipeByIngredientReqDTO> ingredientList) {
        this.page = page;
        this.size = size;
        this.ingredientList = ingredientList;
    }

    @Override
    public String toString() {
        return "tempDTO{" +
                "page=" + page +
                ", size=" + size +
                ", ingredientList=" + ingredientList +
                '}';
    }
}
