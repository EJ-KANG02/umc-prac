package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }

        return User.builder()
                .userId(request.getUserId())
                .region(request.getRegion())
                .userName(request.getUserName())
                .gender(gender)
                .birth(request.getBirth())
                .address(request.getAddress())
                .userFavFoodList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .userMissionList(new ArrayList<>())
                .build();
    }
}
