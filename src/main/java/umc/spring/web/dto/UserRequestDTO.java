package umc.spring.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.Region;
import umc.spring.domain.enums.Role;
import umc.spring.validation.annotation.ExistCategories;

import java.util.Date;
import java.util.List;

public class UserRequestDTO {

    @Getter
    @Setter //@ModelAttribute 가 바인딩 하기 위해 필요
    public static class JoinDto{

        @NotNull
        Long userRegionId;

        @NotBlank
        String  userName;

        @NotBlank
        @Email
        String email;

        @NotBlank
        String password;

        @NotNull(message = "Gender is required")
        Integer gender;

        @Size(min = 5, max = 12)
        String  address;

        @ExistCategories
        List<Long> userFavFoodIdList;

        @NotNull
        Role role;
    }
}
