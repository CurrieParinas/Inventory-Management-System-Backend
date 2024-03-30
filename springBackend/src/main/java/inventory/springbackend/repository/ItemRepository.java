package inventory.springbackend.repository;

import inventory.springbackend.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByItemId(Long itemId);
}
