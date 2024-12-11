package umc.spring.service.UserService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;

import java.util.Optional;

public interface UserQueryService {

    Optional<User> findUser(Long id);

    public Page<Review> getReviewListByUserId(Long UserId, Integer page);
}
