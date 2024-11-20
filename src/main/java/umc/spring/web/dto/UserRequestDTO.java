package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.domain.Region;
import umc.spring.validation.annotation.ExistCategories;

import java.util.Date;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto{
        //Long    userId;
        //Region  region;

        @NotBlank
        String  userName;

        @NotNull
        Integer gender;
        //Date    birth;

        @Size(min = 5, max = 12)
        String  address;

        @ExistCategories
        List<Long> userFavFoodIdList;
        //List<Long> reviewIdList;
        //List<Long> missionIdList;
    }
}
