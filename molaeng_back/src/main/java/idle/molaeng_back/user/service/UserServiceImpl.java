package idle.molaeng_back.user.service;

import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.repository.ReviewRepository;
import idle.molaeng_back.user.model.*;
import idle.molaeng_back.user.model.DTO.UserProfileRequest;
import idle.molaeng_back.user.model.DTO.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final GugunRepository gugunRepository;

    @Autowired
    private final ReviewRepository reviewRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, GugunRepository gugunRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.gugunRepository = gugunRepository;
        this.reviewRepository = reviewRepository;
    }

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
        Gugun newRegion = gugunRepository.findByGugunId(userProfileRequest.getGugunId());

        targetUser.changeProfile(userProfileRequest, newRegion);
        userRepository.save(targetUser);
    }

    @Transactional
    @Override
    public void deleteUserAccount(long userId) {
        // user와 연결된 테이블은 총 4개 - Recipe_like, Review_like, Review, Diary

        // 리뷰는 user_id를 탈퇴한 사용자로 변경
        List<Review> reviewList = reviewRepository.findAllByUserUserId(userId);

        // id가 1인 사용자는 탈퇴한 회원 더미데이터로 설정한 뒤, 작성한 리뷰를 탈퇴한 회원의 리뷰로 변경
        User withdrawnUser = userRepository.findByUserId((long)1);

        for(int i = 0; i < reviewList.size(); i++){
            reviewList.get(i).changeUser(withdrawnUser);
            reviewRepository.save(reviewList.get(i));
        }

        // user 삭제하면 recipe_like, review_like, diary 도 같이 삭제된다.
        userRepository.deleteById(userId);
    }
}
