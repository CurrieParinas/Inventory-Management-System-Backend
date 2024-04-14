package inventory.springbackend.repository;

import inventory.springbackend.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LocationRepository extends JpaRepository<Location,Long> {

    Location findByLocationId(Long locationId);

    @Query(
            value = "SELECT * " +
                    "FROM LOCATION " +
                    "ORDER BY LAST_MODIFIED DESC " +
                    "FETCH FIRST 5 ROWS ONLY",
            nativeQuery = true
    )
    List<Location> findFiveLastModified();
}
