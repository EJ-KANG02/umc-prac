package umc.spring.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Store addStore(StoreRequestDTO.AddStoreDTO request) {

        Store store = StoreConverter.toStore(request);

        //입력받은 지역 ID와 일치하는 값 repository에서 추출 (없으면 예외처리)
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        //region <-> store 양방향 매핑
        region.addStore(store);

        return storeRepository.save(store);
    }

    @Override
    public Review addReview(Long userId, Long storeId, StoreRequestDTO.AddReviewDTO request) {
        Review review = StoreConverter.toReview(request);

        //입력받은 스토어 ID와 일치하는 값 repository에서 추출 (없으면 예외처리)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        //입력받은 유저 ID와 일치하는 값 repository에서 추출 (없으면 예외처리)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.USER_NOT_FOUND));

        //store <-> review 양방향 매핑
        store.addReview(review);
        //user <-> review 양방향 매핑
        user.addReview(review);

        return reviewRepository.save(review);
    }
}
