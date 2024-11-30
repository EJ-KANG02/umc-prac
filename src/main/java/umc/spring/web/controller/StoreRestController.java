package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/save")
    public ApiResponse<StoreResponseDTO.AddStoreResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.AddStoreDTO request){
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toAddStoreResultDTO(store));
    }

    @PostMapping("/{storeId}/save/reviews")
    public ApiResponse<StoreResponseDTO.AddReviewResultDTO> addReview(@RequestBody @Valid StoreRequestDTO.AddReviewDTO request,
                                                                      @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                      @ExistUser @RequestParam(name = "userId") Long userId){
        Review review = storeCommandService.addReview(storeId, userId, request);
        return ApiResponse.onSuccess(StoreConverter.toAddReviewResultDTO(review));
    }

    @PostMapping("/{storeId}/save/missions")
    public ApiResponse<StoreResponseDTO.AddMissionResultDTO> addMission(@RequestBody @Valid StoreRequestDTO.AddMissionDTO request,
                                                                          @ExistStore @PathVariable(name = "storeId") Long storeId){
        Mission mission = storeCommandService.addMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toAddMissionResultDTO(mission));
    }
}