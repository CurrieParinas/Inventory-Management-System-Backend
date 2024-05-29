package inventory.springbackend.repository;

import inventory.springbackend.entities.Location;
import inventory.springbackend.entities.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MediumRepository extends JpaRepository<Medium,Long> {

    Medium findByMediumId(Long mediumId);

    @Query(
            value = "SELECT * " +
                    "FROM MEDIUM " +
                    "ORDER BY LAST_MODIFIED DESC " +
                    "FETCH FIRST 5 ROWS ONLY",
            nativeQuery = true
    )
    List<Medium> findFiveLastModified();

    List<Medium> findMediumByParentLocation(Location parentLocation);
}
