package umc.spring.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.RegionConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Region addStore(StoreRequestDTO.AddStoreDto request) {

        //입력받은 region id와 일치하는 region 조회 (없으면 예외처리)
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        //입력받은 store ID 리스트와 일치하는 값 repository에서 추출 (없으면 예외처리)
        List<Store> storeList = request.getStoreIdList().stream()
                .map(store -> {
                    return storeRepository.findById(store).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
                }).collect(Collectors.toList());


        storeList.forEach(store -> region.addStore(store));

        return region;
    }
}
