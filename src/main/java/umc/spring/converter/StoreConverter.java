package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static ReviewResponseDTO.AddReviewResultDTO toAddReviewResultDTO(Store store){
        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .storeId(store.getStoreId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
