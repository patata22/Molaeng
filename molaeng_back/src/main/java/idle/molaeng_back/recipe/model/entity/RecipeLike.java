package idle.molaeng_back.recipe.model.entity;

import idle.molaeng_back.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RecipeLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_like_id")
    private long recipeLikeId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeLike(long recipeLikeId, User user, Recipe recipe) {
        this.recipeLikeId = recipeLikeId;
        this.user = user;
        this.recipe = recipe;
    }
}
