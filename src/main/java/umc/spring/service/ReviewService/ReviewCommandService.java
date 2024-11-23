package umc.spring.service.ReviewService;

import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Store addReview(ReviewRequestDTO.AddReviewDto request);
}
