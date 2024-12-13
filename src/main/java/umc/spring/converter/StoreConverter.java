package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.AddStoreDTO request){

        return Store.builder()
                .region(null)
                .storeName(request.getStoreName())
                .address(request.getAddress())
                .build();
    }

    public static StoreResponseDTO.AddStoreResultDTO toAddStoreResultDTO(Store store){
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .storeId(store.getStoreId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.AddReviewDTO request){

        return Review.builder()
                .reviewContent(request.getReviewContent())
                .score(request.getScore())
                .build();
    }

    public static StoreResponseDTO.AddReviewResultDTO toAddReviewResultDTO(Review review){
        return StoreResponseDTO.AddReviewResultDTO.builder()
                .reviewId(review.getReviewId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(StoreRequestDTO.AddMissionDTO request){

        return Mission.builder()
                .description(request.getDescription())
                .build();
    }

    public static StoreResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Mission mission){
        return StoreResponseDTO.AddMissionResultDTO.builder()
                .missionId(mission.getMissionId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UserMission toUserMission(StoreRequestDTO.ChallengeMissionDTO request){

        return UserMission.builder()
                .build();
    }

    public static StoreResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(UserMission userMission){
        return StoreResponseDTO.ChallengeMissionResultDTO.builder()
                .userMissionId(userMission.getUserMissionId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getUserName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .reviewContent(review.getReviewContent())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::toReviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission){
        return StoreResponseDTO.MissionPreViewDTO.builder()
                .storeName(mission.getStore().getStoreName())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .description(mission.getDescription())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionList){
        List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(StoreConverter::toMissionPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }


}
