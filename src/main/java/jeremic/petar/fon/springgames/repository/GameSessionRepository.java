package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.GameSession;
import jeremic.petar.fon.springgames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    @Override
    <S extends GameSession> S save(S s);

    List<GameSession> findAllByCreator(User creator);

    Optional<GameSession> findById(Long id);
}
