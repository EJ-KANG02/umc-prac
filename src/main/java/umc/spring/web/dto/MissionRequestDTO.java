package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDto{

        @NotNull
        Long storeId;

        @ExistCategories
        List<Long> missionIdList;
    }
}
