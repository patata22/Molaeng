package idle.molaeng_back.search.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IngredientResDTO {
    long ingredientId;
    String IngredientName;

    public IngredientResDTO(long ingredientId, String ingredientName) {
        this.ingredientId = ingredientId;
        IngredientName = ingredientName;
    }
}
