package idle.molaeng_back.outeat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.user.model.Gugun;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Outeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="outeat_id")
    private long outeatId;

    @Column(name="outeat_price")
    private long outeatPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="gugun_id")
    private Gugun gugun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Builder
    public Outeat(long outeatId, int outeatPrice, Gugun gugun, Recipe recipe) {
        this.outeatId = outeatId;
        this.outeatPrice = outeatPrice;
        this.gugun = gugun;
        this.recipe = recipe;
    }
}
