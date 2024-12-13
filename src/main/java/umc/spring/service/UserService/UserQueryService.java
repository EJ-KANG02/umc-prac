package umc.spring.service.UserService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;

import java.util.Optional;

public interface UserQueryService {

    Optional<User> findUser(Long id);

    Page<Review> getReviewListByUserId(Long UserId, Integer page);

    Page<UserMission> getOngoingMissionListByUserId(Long userId, Integer page);
}
