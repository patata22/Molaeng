package idle.molaeng_back.user.service;

import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.repository.ReviewRepository;
import idle.molaeng_back.user.model.*;
import idle.molaeng_back.user.model.DTO.LoginResDTO;
import idle.molaeng_back.user.model.DTO.UserProfileRequest;
import idle.molaeng_back.user.model.DTO.UserProfileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final GugunRepository gugunRepository;
    private final ReviewRepository reviewRepository;

    public UserServiceImpl(UserRepository userRepository, GugunRepository gugunRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.gugunRepository = gugunRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public UserProfileResponse getUserProfile(long userId) {
        User user = userRepository.findByUserId(userId);
        UserProfileResponse result = UserProfileResponse.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .myRegionId(user.getGugun().getGugunId())
                .build();
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

    @Override
    public boolean isMember(long uuid) {
        int count = userRepository.countUserByUuid(uuid);

        return count >= 1;
    }


    @Override
    public User joinUser(String nickname, long uuid) {
        // 최초 가입자의 거주지역은 0번 더미 지역으로 설정함
        Gugun gugun = gugunRepository.findByGugunId(0);
        System.out.println(gugun.getGugunId());
        User member = User.builder()
                .nickname(nickname)
                .uuid(uuid)
                .gugun(gugun)
                .build();
        User user = userRepository.save(member);
        return user;
    }

    @Override
    public User getUser(long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User findByUuid(long uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public LoginResDTO Login(long uuid, String nickname) {
        LoginResDTO result;
        if(userRepository.countUserByUuid(uuid)==1){
            User user = userRepository.findByUuid(uuid);
            result = LoginResDTO.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .build();
            return result;
        }else{
            User user = joinUser(nickname, uuid);
            result = LoginResDTO.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .build();
            return result;
        }
    }
}
