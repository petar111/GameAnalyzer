package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.PlayerMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
}
