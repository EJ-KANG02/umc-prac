package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserFavFood;

import java.util.List;
import java.util.stream.Collectors;

public class UserFavFoodConverter {
    public static List<UserFavFood> toUserFavFoodList(List<FoodCategory> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        UserFavFood.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
