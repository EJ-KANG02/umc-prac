package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.UserFavFood;
import umc.spring.domain.mapping.UserMission;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto{
        Long    userId;
        Region  region;
        String  userName;
        Integer gender;
        Date    birth;
        String  address;
        List<Long> UserFavFoodIdList;
        List<Long> reviewIdList;
        List<Long> MissionIdList;
    }
}
