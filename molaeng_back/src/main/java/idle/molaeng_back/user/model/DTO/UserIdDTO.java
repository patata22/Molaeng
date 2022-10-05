package idle.molaeng_back.user.model.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIdDTO {
    long userId;

    public UserIdDTO(long userId) {
        this.userId = userId;
    }
}
