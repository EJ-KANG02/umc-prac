package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.UserMissionRepository;
import umc.spring.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewListByUserId(Long userId, Integer page) {
        User user = userRepository.findById(userId).get();

        Page<Review> userPage = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return userPage;
    }

    @Override
    public Page<UserMission> getOngoingMissionListByUserId(Long userId, Integer page) {
        User user = userRepository.findById(userId).get();

        Page<UserMission> userPage = userMissionRepository.findAllByUserAndStatus (user, MissionStatus.ONGOING,PageRequest.of(page, 10));
        return userPage;
    }
}
