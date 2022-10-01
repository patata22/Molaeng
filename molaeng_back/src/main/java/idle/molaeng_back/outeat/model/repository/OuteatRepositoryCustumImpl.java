package idle.molaeng_back.outeat.model.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import idle.molaeng_back.outeat.model.QOuteat;
import idle.molaeng_back.user.model.QGugun;
import idle.molaeng_back.user.model.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class OuteatRepositoryCustumImpl implements OuteatRepositoryCustum {

    private final JPAQueryFactory queryFactory;

    QOuteat outeat = QOuteat.outeat;
    QUser user = QUser.user;

    QGugun gugun = QGugun.gugun;
    @Override
    public Map<String, Object> findOuteatPriceByRecipeIdAndUserUserId(long recipeId, long userId) {
        Map<String, Object> resultMap = new HashMap<>();
        String gugunName="";
        long outeatPrice=0;
        try{
            gugunName = queryFactory.select(gugun.gugunName)
                    .from(gugun)
                    .where(gugun.gugunId.eq(JPAExpressions
                            .select(user.gugun.gugunId)
                            .from(user)
                            .where(user.userId.eq(userId)))).fetchOne();
            try {
                outeatPrice = queryFactory.select(outeat.outeatPrice)
                        .from(outeat)
                        .where(outeat.recipe.recipeId.eq(recipeId)
                        .and(outeat.gugun.gugunId.eq(JPAExpressions
                            .select(user.gugun.gugunId)
                            .from(user)
                            .where(user.userId.eq((userId)))))).fetchOne();
            }catch (NullPointerException e){

            }
            resultMap.put("my",outeatPrice);
            resultMap.put("gugunName",gugunName);
        }catch(Exception e){
        }
        return resultMap;
    }

    @Override
    public long findOuteatPriceByRecipeId(long recipeId) {
        try {
            return queryFactory.select(outeat.outeatPrice)
                    .from(outeat)
                    .where(outeat.recipe.recipeId.eq(recipeId)
                            .and(outeat.gugun.gugunId.isNull())).fetchOne();
        }catch (NullPointerException e){
            return 0;
        }

    }

}
