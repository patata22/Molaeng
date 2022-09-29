package idle.molaeng_back.outeat.service;

import idle.molaeng_back.outeat.model.repository.OuteatRepositoryCustum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OuteatServiceImpl implements OuteatService{

    @Autowired
    private OuteatRepositoryCustum outeatRepo;

    @Override
    public long outeatSeoul(long recipeId) {
        return outeatRepo.findOuteatPriceByRecipeId(recipeId);
    }

    @Override
    public Map<String, Object> outeatGugun(long recipeId, long userId) {
        return outeatRepo.findOuteatPriceByRecipeIdAndUserUserId(recipeId, userId);
    }
}
