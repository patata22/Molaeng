package idle.molaeng_back.search.service;

import idle.molaeng_back.recipe.model.entity.Ingredient;
import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.repository.IngredientRepository;
import idle.molaeng_back.recipe.model.repository.RecipeLikeRepository;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.search.DTO.response.IngredientResDTO;
import idle.molaeng_back.search.DTO.response.RecipeNameResDTO;
import idle.molaeng_back.search.DTO.response.RecipeResDTO;
import idle.molaeng_back.search.DTO.response.SearchRecipeResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final RecipeRepository recipeRepository;
    private final RecipeLikeRepository RecipeLikeRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public SearchRecipeResDTO searchRecipeByIngredient(List<Long> ingredientIdList, Pageable pageable, long userId) {
        HashMap<Recipe, Integer> countMap = new HashMap<>();
        List<Recipe> recipeList = recipeRepository.findAll();
        for (Recipe recipe : recipeList) {
            countMap.put(recipe, 0);
        }
        for (Long ingredientId : ingredientIdList) {
            Ingredient ingredient= ingredientRepository.findByIngredientId(ingredientId);
            for (RecipeIngredient recipeIngredient : ingredient.getRecipeIngredientList()) {
                Recipe recipe = recipeIngredient.getRecipe();
                countMap.put(recipe, countMap.get(recipe)+1);
            }
        }
        recipeList.sort((o1,o2) -> (countMap.get(o2)-countMap.get(o1)));
        long start = pageable.getOffset();
        long end = Math.min((start + pageable.getPageSize()), recipeList.size());
        List<Recipe> subList = recipeList.subList((int) start, (int) end);
        boolean hasNext = start + pageable.getPageSize() < recipeList.size();
        List<RecipeResDTO> tempList = new ArrayList<>();
        for (Recipe recipe : subList) {
            long id = recipe.getRecipeId();
            tempList.add(RecipeResDTO.builder()
                    .recipeId(id)
                    .recipeName(recipe.getRecipeName())
                    .ingredientList(IngredientToDTO(recipe))
                    .recipeImage(recipe.getRecipeImage())
                    .recipeKcal(recipe.getRecipeKcal())
                    .isLiked(RecipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, id))
                    .avgScore(getAvgScore(recipe))
                    .build());
        }
        return SearchRecipeResDTO.builder()
                .hasNext(hasNext)
                .recipeList(tempList)
                .build();
    }



    @Override
    public SearchRecipeResDTO searchRecipeByCalory(Pageable pageable, long userId) {
        Slice<Recipe> recipeList = recipeRepository.findAll(pageable);
        List<RecipeResDTO> tempList= new ArrayList<>();
        for (Recipe recipe : recipeList) {
            long id = recipe.getRecipeId();
            tempList.add(RecipeResDTO.builder()
                    .recipeId(id)
                    .recipeName(recipe.getRecipeName())
                    .ingredientList(IngredientToDTO(recipe))
                    .recipeImage(recipe.getRecipeImage())
                    .recipeKcal(recipe.getRecipeKcal())
                    .isLiked(RecipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, id))
                    .avgScore(getAvgScore(recipe))
                    .build());
        }
        return SearchRecipeResDTO.builder()
                .hasNext(recipeList.hasNext())
                .recipeList(tempList)
                .build();
    }

    @Override
    public List<RecipeNameResDTO> searchAllRecipe() {
        return recipeRepository.findAll().stream()
                .map(x -> new RecipeNameResDTO(x.getRecipeId(), x.getRecipeName()))
                .collect(Collectors.toList());
    }

    @Override
    public SearchRecipeResDTO searchRecipeByScore(Pageable pageable, long userId) {
        List<Recipe> recipeList = recipeRepository.findAll();
        recipeList.sort((o1,o2) -> (int) (getAvgScore(o2)-getAvgScore(o1)));
        long start = pageable.getOffset();
        long end = Math.min((start + pageable.getPageSize()), recipeList.size());
        List<Recipe> subList = recipeList.subList((int) start, (int) end);
        boolean hasNext = start + pageable.getPageSize() < recipeList.size();
        List<RecipeResDTO> tempList = new ArrayList<>();
        for (Recipe recipe : subList) {
            long id = recipe.getRecipeId();
            tempList.add(RecipeResDTO.builder()
                    .recipeId(id)
                    .recipeName(recipe.getRecipeName())
                    .ingredientList(IngredientToDTO(recipe))
                    .recipeImage(recipe.getRecipeImage())
                    .recipeKcal(recipe.getRecipeKcal())
                    .isLiked(RecipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, id))
                    .avgScore(getAvgScore(recipe))
                    .build());
        }
        return SearchRecipeResDTO.builder()
                .hasNext(hasNext)
                .recipeList(tempList)
                .build();
    }

    //평점 추출용 메서드
    private double getAvgScore(Recipe recipe) {
        int[] scoreCnt = new int[5];
        int total=0;
        scoreCnt[0] = recipe.getOneScore();
        scoreCnt[1] = recipe.getTwoScore();
        scoreCnt[2] = recipe.getThreeScore();
        scoreCnt[3] = recipe.getFourScore();
        scoreCnt[4] = recipe.getFiveScore();
        int size=0;
        for(int i=0;i<5;i++){
            total += scoreCnt[i]*(i+1);
            size += scoreCnt[i];
        }
        double avgScore = Math.round(((double)total*10/size)/10);
        return avgScore;
    }

    //재료 추출용 메서드
    private List<IngredientResDTO> IngredientToDTO(Recipe recipe) {
        List<IngredientResDTO> tempIngRes = new ArrayList<>();
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredientList()) {
            tempIngRes.add(new IngredientResDTO(recipeIngredient.getIngredient().getIngredientId(), recipeIngredient.getIngredient().getIngredientName()));
        }
        return tempIngRes;
    }

}
