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

    @Query(
            value = "SELECT * " +
                    "FROM LOCATION " +
                    "WHERE PARENT_LOCATION IS NULL",
            nativeQuery = true
    )
    List<Location> findLocationsWithNoParent();

    @Query(
            value = "SELECT COUNT(*) - 1 " +
                    "FROM LOCATION L " +
                    "START WITH L.LOCATION_ID = ? " +
                    "CONNECT BY PRIOR L.LOCATION_ID = L.PARENT_LOCATION ",
            nativeQuery = true
    )
    Long countChildren(Long locationId);

    @Query(
            value = "SELECT * " +
                    "FROM LOCATION " +
                    "WHERE PARENT_LOCATION IS NULL AND LOCATION_ID != ?",
            nativeQuery = true
    )
    List<Location> findAvailableLocationsForEdit(Long locationId);
}
