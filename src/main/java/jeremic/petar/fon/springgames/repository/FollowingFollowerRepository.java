package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.FollowingFollower;
import jeremic.petar.fon.springgames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowingFollowerRepository extends JpaRepository<FollowingFollower, Long> {

    List<FollowingFollower> findAllByFollowed(User user);
    List<FollowingFollower> findAllByFollower(User user);

}
