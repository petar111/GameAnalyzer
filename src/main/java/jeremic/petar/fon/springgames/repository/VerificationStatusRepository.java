package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationStatusRepository extends JpaRepository<VerificationStatus, Long> {
}
