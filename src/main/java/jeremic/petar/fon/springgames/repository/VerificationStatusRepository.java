package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationStatusRepository extends JpaRepository<VerificationStatus, Long> {

    Optional<VerificationStatus> findByName(String name);
}
