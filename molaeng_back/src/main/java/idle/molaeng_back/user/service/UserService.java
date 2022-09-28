package idle.molaeng_back.user.service;

import idle.molaeng_back.user.model.DTO.UserProfileRequest;
import idle.molaeng_back.user.model.DTO.UserProfileResponse;

public interface UserService {

    UserProfileResponse getUserProfile(long userId);
    void updateUserProfile(UserProfileRequest userProfileRequest);
    void deleteUserProfile(long userId);
}
