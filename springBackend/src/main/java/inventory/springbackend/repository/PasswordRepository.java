package inventory.springbackend.repository;

import inventory.springbackend.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password,Long> {

    Password findByPasswordId(Long passwordId);
}
