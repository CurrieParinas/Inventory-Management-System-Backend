package inventory.springbackend.repository;

import inventory.springbackend.entities.Medium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediumRepository extends JpaRepository<Medium,Long> {

    Medium findByMediumId(Long mediumId);
}
