package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long> {
}
