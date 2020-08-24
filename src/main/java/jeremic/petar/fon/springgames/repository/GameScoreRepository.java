package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameScoreRepository extends JpaRepository<GameScore, Long> {
}
