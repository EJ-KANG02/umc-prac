package umc.spring.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.apiPayload.exception.handler.UserMissionHandler;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserFavFoodConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Mission;
import umc.spring.domain.Region;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserFavFood;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.*;
import umc.spring.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final RegionRepository regionRepository;
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request) {

        User newUser = UserConverter.toUser(request);

        //입력받은 음식 카테고리 ID 리스트와 일치하는 값 repository에서 추출 (없으면 예외처리)
        List<FoodCategory> foodCategoryList = request.getUserFavFoodIdList().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        //입력받은 지역 ID와 일치하는 값 repository에서 추출 (없으면 예외처리)
        Region region = regionRepository.findById(request.getUserRegionId())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        List<UserFavFood> userFavFoodList = UserFavFoodConverter.toUserFavFoodList(foodCategoryList);

        userFavFoodList.forEach(userFavFood -> {userFavFood.setUser(newUser);});

        //user <-> region 양방향 매핑
        region.addUser(newUser);

        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public UserMission completeMission(Long userId, Long missionId) {
        User user = userRepository.findById(userId).get();

        Mission mission = missionRepository.findById(missionId).get();

        UserMission userMission = userMissionRepository.findByUserAndMission(user,mission)
                .orElseThrow(() -> new UserMissionHandler(ErrorStatus.USER_MISSION_NOT_FOUND));

        userMission.setStatus(MissionStatus.COMPLETED);

        return userMissionRepository.save(userMission);
    }
}
