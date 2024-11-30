package umc.spring.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.*;
import umc.spring.domain.*;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Store addMission(MissionRequestDTO.AddMissionDto request) {

        //입력받은 store id와 일치하는 store 조회 (없으면 예외처리)
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        //입력받은 mission ID 리스트와 일치하는 값 repository에서 추출 (없으면 예외처리)
        List<Mission> missionList = request.getMissionIdList().stream()
                .map(review -> {
                    return missionRepository.findById(review).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
                }).collect(Collectors.toList());


        // Store에 Mission 추가 (연관 관계 설정 포함)
        missionList.forEach(mission -> store.addMission(mission));

        return store;
    }
}
