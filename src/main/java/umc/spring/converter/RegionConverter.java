package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RegionConverter {

    public static StoreResponseDTO.AddStoreResultDTO toAddStoreResultDTO(Region region){
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .regionId(region.getRegionId())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
