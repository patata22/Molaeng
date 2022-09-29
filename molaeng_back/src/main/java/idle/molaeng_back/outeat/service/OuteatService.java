package idle.molaeng_back.outeat.service;

import java.util.Map;

public interface OuteatService {

    public long outeatSeoul(long recipeId);
    public Map<String, Object> outeatGugun(long recipeId, long userId);

}
