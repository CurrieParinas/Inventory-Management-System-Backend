package inventory.springbackend.repository;

import inventory.springbackend.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Long> {

    Record findByRecordId(Long recordId);
}
