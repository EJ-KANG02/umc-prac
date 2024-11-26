package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;
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

    public static ReviewResponseDTO.AddReviewResultDTO toAddReviewResultDTO(Store store){
        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .storeId(store.getStoreId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Store store){
        return MissionResponseDTO.AddMissionResultDTO.builder()
                .storeId(store.getStoreId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
