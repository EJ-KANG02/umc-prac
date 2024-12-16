package umc.spring.service.UserService;

import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.UserRequestDTO;

public interface UserCommandService {
    User joinUser(UserRequestDTO.JoinDto request);

    UserMission completeMission(Long userId, Long missionId);
}
