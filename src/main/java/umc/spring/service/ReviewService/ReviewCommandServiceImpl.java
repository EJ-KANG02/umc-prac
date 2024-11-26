package umc.spring.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.ReviewHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Store addReview(ReviewRequestDTO.AddReviewDto request) {

        //입력받은 store id와 일치하는 store 조회 (없으면 예외처리)
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        //입력받은 review ID 리스트와 일치하는 값 repository에서 추출 (없으면 예외처리)
        List<Review> reviewList = request.getReviewIdList().stream()
                .map(review -> {
                    return reviewRepository.findById(review).orElseThrow(() -> new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND));
                }).collect(Collectors.toList());

        // Store에 Review 추가 (연관 관계 설정 포함)
        reviewList.forEach(review -> store.addReview(review));

        return store;
    }
}