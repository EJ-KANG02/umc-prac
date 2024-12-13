package umc.spring.converter;


import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .region(null) //id 예외 처리 후 대입
                .userName(request.getUserName())
                .gender(gender)
                .address(request.getAddress())
                .userFavFoodList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .userMissionList(new ArrayList<>())
                .build();
    }

    public static UserResponseDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review){
        return UserResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getUserName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .reviewContent(review.getReviewContent())
                .build();
    }

    public static UserResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList){
        List<UserResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(UserConverter::toReviewPreViewDTO).collect(Collectors.toList());

        return UserResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
