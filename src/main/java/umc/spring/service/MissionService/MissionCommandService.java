package umc.spring.service.MissionService;

import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Store addMission(MissionRequestDTO.AddMissionDto request);
}
