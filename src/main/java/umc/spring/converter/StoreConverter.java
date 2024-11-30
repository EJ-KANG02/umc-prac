package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

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

    //여기 고쳐야함
    public static MissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Store store){
        return MissionResponseDTO.AddMissionResultDTO.builder()
                .storeId(store.getStoreId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
