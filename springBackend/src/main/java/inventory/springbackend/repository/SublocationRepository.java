package inventory.springbackend.repository;

import inventory.springbackend.entities.Sublocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SublocationRepository extends JpaRepository<Sublocation,Long> {

    Record findBySublocationId(Long sublocationId);
}
