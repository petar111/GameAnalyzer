package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.GameScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameScoreRepository extends JpaRepository<GameScore, Long> {

    List<GameScore> findAllByNumberOfRoundsAndGameOrderByTotalPayoffDesc(int numberOfRounds, Game game, Pageable page);
}
