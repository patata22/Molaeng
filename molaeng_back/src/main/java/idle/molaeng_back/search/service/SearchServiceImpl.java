package idle.molaeng_back.search.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.search.DTO.response.RecipeNameResDTO;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final RecipeRepository recipeRepository;

    public SearchServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    //여기도 결국 페이징이 들어가야한다
    @Override
    public List<Recipe> searchRecipeByIngredient(List<Long> ingredientIdList) {
//         recipeRepository가 구현되면 아래로 대체한다
//         List<Recipe> recipeList = recipeRepository.findAll();
//        List<Recipe> recipeList = new ArrayList<>();
//        // 이런식으로 하면 페이징으로 할때마다 조회해야하는데
//        // redis?
//        //레시피부터 시작하지말고 재료부터 찾아가기?
//        Map<Recipe, Integer> countMap = new HashMap<>();
//        for (Long ingredientId : ingredientIdList) {
//             List<RecipeIngredient> RecipeIngredientList = ingredientRepository.findByIngredientId(ingredientId).getRecipeIngredint();
//             for(RecipeIngredient recipeIngredient : RecipeIngredientList ){
//                 Recipe recipe = recipeIngredient.getRecipe();
//                 long recipeId = recipe.getRecipeId();
//                 if(!countMap.containsKey(recipe)){
//                     countMap.put(recipe, 1);
//                 }else{
//                     countMap.put(recipe, countMap.get(recipe)+1);
//                 }
//             }
//        }
//        class temp {
//            Recipe recipe;
//            int count;
//
//            public temp(Recipe recipe, int count) {
//                this.recipe = recipe;
//                this.count = count;
//            }
//        }
//        List<temp> result = new ArrayList<>();
//        for (Recipe recipe : countMap.keySet()) {
//            result.add(new temp(recipe, countMap.get(recipe)));
//        }
//        result.sort(new Comparator<temp>() {
//            @Override
//            public int compare(temp o1, temp o2) {
//                return o2.count-o1.count;
//            }
//        });
////        ---> result 대상으로 다시 돌려서 dto로 변환한다!
//
//
//
//        //여기는 레시피기준으로 찾기
//        for (Recipe recipe : recipeList) {
//            int count= 0;
//            List<RecipeIngredient> recipeIngredientList = recipe.getRecipeIngredientList();
//            //여기도 너무 쿼리 많이 튀어나갈 것 같으니 fetch join 고려해야할듯
//            for (RecipeIngredient recipeIngredient : recipeIngredientList) {
//                if(ingredientIdList.contains (recipeIngredient.getIngredient().getIngredientId())){
//                    count+=1;
//                }
//            }
//            result.add(new Object[]{recipe, count});
//        }
//        result.sort(new Comparator<Object[]>() {
//            @Override
//            public int compare(Object[] o1, Object[] o2) {
//                return (int)o2[1]-(int)o1[1];
//            }
//        });
//        return result;

        return null;
    }

    @Override
    public List<Recipe> findRecipeByName(String keyword) {
//        //평점 순으로 하는게 맞으려나? 추후 논의 필요
//        List<Recipe> result = recipeRepository.findByRecipeNameContains(String keyword);
//        return result;
        return null;
    }

    @Override
    public List<RecipeNameResDTO> searchAllRecipe() {
        return recipeRepository.findAll().stream().map(x -> new RecipeNameResDTO(x.getRecipeId(), x.getRecipeName())).collect(Collectors.toList());
    }
}
