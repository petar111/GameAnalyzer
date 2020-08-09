package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long> {

    @Override
    <S extends Strategy> List<S> saveAll(Iterable<S> iterable);
}
