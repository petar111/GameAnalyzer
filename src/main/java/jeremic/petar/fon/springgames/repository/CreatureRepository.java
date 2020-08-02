package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long> {

    Optional<Creature> findById(Long id);

}
