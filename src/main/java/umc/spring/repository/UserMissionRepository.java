package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.User;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findAllByUserAndStatus(User user, MissionStatus status, PageRequest pageRequest);
}
