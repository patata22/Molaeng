package idle.molaeng_back.user.service;

import idle.molaeng_back.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GugunRepository gugunRepository;

    @Override
    public UserProfileResponse getUserProfile(long userId) {
        User user = userRepository.findByUserId(userId);
        UserProfileResponse result = new UserProfileResponse(user.getNickname(), user.getGugun().getGugunName());

        return result;
    }

    @Transactional
    @Override
    public void updateUserProfile(UserProfileRequest userProfileRequest) {
        User targetUser = userRepository.findByUserId(userProfileRequest.getUserId());
        Gugun newRegion = gugunRepository.findByGugunName(userProfileRequest.getGugunName());

//        targetUser.changeProfile(userProfileRequest, newRegion);
        userRepository.save(targetUser);
    }

    @Override
    public void deleteUserProfile(int userId) {

        // user와 연결된 테이블은 총 4개 - Recipe_like, Review_like, Review, Diary
        // 리뷰와 리뷰 좋아요는 user_id를 탈퇴한 사용자로 변경
        // 레시피 좋아요와 모랭 다이어리는 삭제
        // user 삭제

    }
}
