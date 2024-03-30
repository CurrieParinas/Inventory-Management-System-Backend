package inventory.springbackend.repository;

import inventory.springbackend.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {

    Location findByLocationId(Long locationId);
}
