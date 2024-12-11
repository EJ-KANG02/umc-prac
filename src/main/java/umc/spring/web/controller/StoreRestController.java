package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

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

    @PostMapping("/{storeId}/missions/{missionId}/challenge")
    public ApiResponse<StoreResponseDTO.ChallengeMissionResultDTO> challengeMission(@RequestBody @Valid StoreRequestDTO.ChallengeMissionDTO request,
                                                                                    @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                    @PathVariable(name = "missionId") Long missionId,
                                                                                    @ExistUser @RequestParam(name = "userId") Long userId){
        UserMission userMission = storeCommandService.addUserMission(request, userId, missionId);
        return ApiResponse.onSuccess(StoreConverter.toChallengeMissionResultDTO(userMission));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewListByStoreId(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewListByStoreId(storeId,page);
        return ApiResponse.onSuccess(StoreConverter.toReviewPreViewListDTO(reviewList));
    }

}