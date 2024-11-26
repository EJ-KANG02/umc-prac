package umc.spring.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
