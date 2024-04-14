package inventory.springbackend.repository;

import inventory.springbackend.entities.ItemMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ItemMediumRepository extends JpaRepository<ItemMedium,Long> {

        ItemMedium findByItemMediumId(Long itemMediumId);

        @Query(
                value = "SELECT * " +
                        "FROM ITEM_MEDIUM IM JOIN ITEM I ON IM.ITEM_ID = I.ITEM_ID " +
                        "WHERE IM.TYPE = 'U' " +
                        "ORDER BY IM.LAST_MODIFIED DESC " +
                        "FETCH FIRST 5 ROWS ONLY",
                nativeQuery = true
        )
        List<Map<String, Object>> findFiveLastModifiedUntracked();

        @Query(
                value = "SELECT * " +
                        "FROM ITEM_MEDIUM IM JOIN ITEM I ON IM.ITEM_ID = I.ITEM_ID " +
                        "WHERE IM.TYPE = 'R' OR IM.TYPE = 'C' " +
                        "ORDER BY IM.LAST_MODIFIED DESC " +
                        "FETCH FIRST 5 ROWS ONLY",
                nativeQuery = true
        )
        List<Map<String, Object>> findFiveLastModifiedTracked();
}
