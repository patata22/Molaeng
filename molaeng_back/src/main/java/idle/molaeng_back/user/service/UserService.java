package idle.molaeng_back.user.service;

import idle.molaeng_back.user.model.UserProfileRequest;
import idle.molaeng_back.user.model.UserProfileResponse;

public interface UserService {

    UserProfileResponse getUserProfile(long userId);
    void updateUserProfile(UserProfileRequest userProfileRequest);
    void deleteUserProfile(int userId);
}
