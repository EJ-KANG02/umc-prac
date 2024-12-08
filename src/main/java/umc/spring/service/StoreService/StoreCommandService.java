package umc.spring.service.StoreService;

import umc.spring.domain.Mission;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.AddStoreDTO request);

    Review addReview(Long userId, Long storeId, StoreRequestDTO.AddReviewDTO request);

    Mission addMission(Long storeId, StoreRequestDTO.AddMissionDTO request);

    UserMission addUserMission(StoreRequestDTO.ChallengeMissionDTO request, Long userId, Long missionId);
}
