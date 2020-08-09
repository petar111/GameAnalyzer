package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findById(Long id);

    Optional<Game> findByName(String name);

    @Override
    <S extends Game> S save(S s);

    @Override
    List<Game> findAll();
}
