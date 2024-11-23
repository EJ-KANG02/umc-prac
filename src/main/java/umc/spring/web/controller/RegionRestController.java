package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RegionConverter;
import umc.spring.domain.Region;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}/stores")
    public ApiResponse<StoreResponseDTO.AddStoreResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.AddStoreDto request){
        Region region = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(RegionConverter.toAddStoreResultDTO(region));
    }
}
