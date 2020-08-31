package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findById(Long id);

    Optional<Game> findByName(String name);

    List<Game> findAllByCreator(User creator);

    @Override
    <S extends Game> S save(S s);

}
