package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class StoreRequestDTO {

    @Getter
    public static class AddStoreDto{

        @NotNull
        Long regionId;

        @ExistCategories
        List<Long> storeIdList;
    }
}