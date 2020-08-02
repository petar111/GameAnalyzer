package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
