package idle.molaeng_back.user.service;

import idle.molaeng_back.user.model.DTO.UserProfileRequest;
import idle.molaeng_back.user.model.DTO.UserProfileResponse;
import idle.molaeng_back.user.model.User;

public interface UserService {

    UserProfileResponse getUserProfile(long userId);
    void updateUserProfile(UserProfileRequest userProfileRequest);
    void deleteUserAccount(long userId);

    boolean isMember(String uuid);

    User joinUser(String nickname, String uuid);

    User getUser(long userId);

    User findByUuid(String uuid);
}
