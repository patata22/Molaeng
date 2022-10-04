package idle.molaeng_back.user.service;

import idle.molaeng_back.user.model.DTO.UserProfileRequest;
import idle.molaeng_back.user.model.DTO.UserProfileResponse;
import idle.molaeng_back.user.model.User;

public interface UserService {

    UserProfileResponse getUserProfile(long userId);
    void updateUserProfile(UserProfileRequest userProfileRequest);
    void deleteUserAccount(long userId);

    boolean isMember(long uuid);

    User joinUser(String nickname, long uuid);

    User getUser(long userId);

    User findByUuid(long uuid);
    long Login(long uuid, String nickname);
}
