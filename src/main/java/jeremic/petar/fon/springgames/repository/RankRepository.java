package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Long> {
    Optional<Rank> findFirstByExperienceMaxGreaterThanEqualAndExperienceMinLessThanEqual(int experienceForMax, int experienceForMin);
}
