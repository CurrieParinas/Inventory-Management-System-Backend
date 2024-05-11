package inventory.springbackend.repository;

import inventory.springbackend.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByItemId(Long itemId);
}
