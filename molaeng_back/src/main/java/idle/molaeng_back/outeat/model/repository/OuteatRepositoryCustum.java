package idle.molaeng_back.outeat.model.repository;


import java.util.Map;

public interface OuteatRepositoryCustum {
    Map<String,Object> findOuteatPriceByRecipeIdAndUserUserId(long recipeId, long userId);

    long findOuteatPriceByRecipeId(long recipeId);
}
