package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.RecipeDetail;
import idle.molaeng_back.recipe.model.repository.RecipeDetailRepository;
import idle.molaeng_back.recipe.model.response.RecipeDetailRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecipeDetailServiceImpl implements RecipeDetailService{
    private final RecipeDetailRepository recipeDetailRepository;

    @Autowired
    public RecipeDetailServiceImpl(RecipeDetailRepository recipeDetailRepository) {
        this.recipeDetailRepository = recipeDetailRepository;
    }

    @Override
    public List<RecipeDetailRes> readRecipeDetailByRecipeId(long recipeId) {
        List<RecipeDetail> recipeDetailList = recipeDetailRepository.findAllByRecipeRecipeId(recipeId);

        ArrayList<RecipeDetailRes> recipeDetailResList = new ArrayList<>();
        for (RecipeDetail recipeDetail : recipeDetailList) {
            RecipeDetailRes recipeDetailRes = RecipeDetailRes.builder()
                    .recipeDetailId(recipeDetail.getRecipeDetailId())
                    .recipeOrder(recipeDetail.getRecipeOrder())
                    .recipeContent(recipeDetail.getRecipeContent())
                    .recipeDetailImg(recipeDetail.getRecipeDetailImg())
                    .build();

            recipeDetailResList.add(recipeDetailRes);
        }

        return recipeDetailResList;
    }
}
