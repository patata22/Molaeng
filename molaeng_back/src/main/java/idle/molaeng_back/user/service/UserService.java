package idle.molaeng_back.user.service;

import idle.molaeng_back.user.model.DTO.UserProfileRequest;
import idle.molaeng_back.user.model.DTO.UserProfileResponse;

public interface UserService {

    UserProfileResponse getUserProfile(long userId);
    void updateUserProfile(UserProfileRequest userProfileRequest);
    void deleteUserAccount(long userId);

    boolean isMember(String uuid);


    long joinUser(String nickname, String uuid);
}
