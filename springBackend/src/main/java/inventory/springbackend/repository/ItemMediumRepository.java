package inventory.springbackend.repository;

import inventory.springbackend.entities.ItemMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ItemMediumRepository extends JpaRepository<ItemMedium,Long> {

        ItemMedium findByItemMediumId(Long itemMediumId);

        @Query(
                value = "SELECT IM.ITEM_MEDIUM_ID, I.NAME, I.DESCRIPTION, I.BRAND, IM.QUANTITY " +
                        "FROM ITEM_MEDIUM IM JOIN ITEM I ON IM.ITEM_ID = I.ITEM_ID " +
                        "WHERE IM.TYPE = 'U' " +
                        "ORDER BY IM.LAST_MODIFIED DESC " +
                        "FETCH FIRST 5 ROWS ONLY",
                nativeQuery = true
        )
        List<Map<String, Object>> findFiveLastModifiedUntracked();

        @Query(
                value = "SELECT IM.ITEM_MEDIUM_ID, I.NAME, I.DESCRIPTION, I.BRAND, IM.QUANTITY " +
                        "FROM ITEM_MEDIUM IM JOIN ITEM I ON IM.ITEM_ID = I.ITEM_ID " +
                        "WHERE IM.TYPE = 'R' OR IM.TYPE = 'C' " +
                        "ORDER BY IM.LAST_MODIFIED DESC " +
                        "FETCH FIRST 5 ROWS ONLY",
                nativeQuery = true
        )
        List<Map<String, Object>> findFiveLastModifiedTracked();

        @Query(
                value = "SELECT ITEM_MEDIUM_ID, I.NAME, I.CODENAME, I.BRAND, M.NAME AS MEDIUM_NAME, L.NAME AS LOCATION_NAME, IM.CREATE_DATE, IM.LAST_MODIFIED\n" +
                        "FROM ITEM_MEDIUM IM JOIN ITEM I ON IM.ITEM_ID = I.ITEM_ID\n" +
                        "                    JOIN MEDIUM M on IM.MEDIUM_ID = M.MEDIUM_ID\n" +
                        "                    JOIN LOCATION L ON M.PARENT_LOCATION = L.LOCATION_ID\n" +
                        "WHERE IM.TYPE = 'U'",
                nativeQuery = true
        )
        List<Map<String, Object>> findAllUntrackedItems();

        @Query(
                value = "SELECT ITEM_MEDIUM_ID, I.NAME, I.CODENAME, I.BRAND, IM.QUANTITY, M.NAME AS MEDIUM_NAME, L.NAME AS LOCATION_NAME, IM.CREATE_DATE, IM.LAST_MODIFIED\n" +
                        "FROM ITEM_MEDIUM IM JOIN ITEM I ON IM.ITEM_ID = I.ITEM_ID\n" +
                        "                    JOIN MEDIUM M on IM.MEDIUM_ID = M.MEDIUM_ID\n" +
                        "                    JOIN LOCATION L ON M.PARENT_LOCATION = L.LOCATION_ID\n" +
                        "WHERE IM.TYPE = 'R' OR IM.TYPE = 'C'",
                nativeQuery = true
        )
        List<Map<String, Object>> findAllTrackedItems();
}
