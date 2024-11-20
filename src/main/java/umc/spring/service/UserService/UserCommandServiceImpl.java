package umc.spring.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserFavFoodConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserFavFood;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request) {

        User newUser = UserConverter.toUser(request);
        List<FoodCategory> foodCategoryList = request.getUserFavFoodIdList().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<UserFavFood> userFavFoodList = UserFavFoodConverter.toUserFavFoodList(foodCategoryList);

        userFavFoodList.forEach(userFavFood -> {userFavFood.setUser(newUser);});

        return userRepository.save(newUser);
    }
}
