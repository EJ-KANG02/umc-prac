package umc.spring.service.StoreService;

import umc.spring.domain.Region;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Region addStore(StoreRequestDTO.AddStoreDto request);
}
