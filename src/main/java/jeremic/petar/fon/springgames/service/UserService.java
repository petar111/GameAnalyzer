package jeremic.petar.fon.springgames.service;


import com.sun.deploy.net.HttpResponse;
import jeremic.petar.fon.springgames.dto.ExperienceUpdateDto;
import jeremic.petar.fon.springgames.dto.FollowingRequestDto;
import jeremic.petar.fon.springgames.dto.HttpResponseBody;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.dto.game.GameInfoDto;
import jeremic.petar.fon.springgames.entity.User;

import java.util.List;

public interface UserService {
    UserDto findByUsername(String username);

    List<UserDto> findAllUsersFollowingById(Long id);

    List<UserDto> findAllUsersFollowersById(Long id);

    int findAllUsersFollowingCountById(Long id);

    int findAllUsersFollowerCountsById(Long id);

    UserDto save(UserDto userDto);

    ExperienceUpdateDto saveExperience(ExperienceUpdateDto experienceUpdateDto);

    List<GameInfoDto> findGamesById(Long id);

    HttpResponseBody<String> follow(FollowingRequestDto followingRequest);

    HttpResponseBody<String> unFollow(FollowingRequestDto followingRequest);

    Boolean isFollowing(Long id, Long followingId);
}
