package idle.molaeng_back.search.service;

import idle.molaeng_back.search.DTO.response.RecipeNameResDTO;
import idle.molaeng_back.search.DTO.response.SearchRecipeResDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SearchService {

    SearchRecipeResDTO searchRecipeByIngredient(List<Long> ingredientIdList, Pageable pageable, long userId);
    SearchRecipeResDTO searchRecipeByCalory(List<Long> ingredientIdList, Pageable pageable, long userId);
    SearchRecipeResDTO searchRecipeByScore(List<Long> ingredientIdList, Pageable pageable, long userId);
    SearchRecipeResDTO searchRecipeByCost(List<Long> ingredientIdList, Pageable pageable, long userId);
    SearchRecipeResDTO searchRecipeByName(Pageable pageable, long userId, String keyWord);
    SearchRecipeResDTO searchRecipeByNoName(Pageable pageable, long userId);
    List<RecipeNameResDTO> searchAllRecipe();

}
