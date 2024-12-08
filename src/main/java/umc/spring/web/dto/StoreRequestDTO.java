package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class StoreRequestDTO {

    @Getter
    public static class AddStoreDTO{

        @NotNull
        Long regionId;

        @NotBlank
        String storeName;

        @Size(min = 5, max = 12)
        String  address;
    }

    @Getter
    public static class AddReviewDTO{

        @NotNull
        Float score;

        @NotBlank
        String reviewContent;
    }

    @Getter
    public static class AddMissionDTO{

        @NotBlank
        String description;
    }

    @Getter
    public static class ChallengeMissionDTO{

    }
}