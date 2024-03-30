package inventory.springbackend.repository;

import inventory.springbackend.entities.ItemMedium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMediumRepository extends JpaRepository<ItemMedium,Long> {

        ItemMedium findByItemMediumId(Long itemMediumId);
}
