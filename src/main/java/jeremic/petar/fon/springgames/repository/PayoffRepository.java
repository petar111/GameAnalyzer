package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Payoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoffRepository extends JpaRepository<Payoff, Long> {
}
