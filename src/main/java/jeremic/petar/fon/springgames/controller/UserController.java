package jeremic.petar.fon.springgames.controller;

import jeremic.petar.fon.springgames.dto.ExperienceUpdateDto;
import jeremic.petar.fon.springgames.dto.FollowingRequestDto;
import jeremic.petar.fon.springgames.dto.HttpResponseBody;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.dto.game.GameInfoDto;
import jeremic.petar.fon.springgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService  userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}/following")
    public List<String> findAllUsersFollowingById(@PathVariable Long id){
        return userService.findAllUsersFollowingById(id).stream().flatMap(
                userDto -> Stream.of(userDto.getUsername())
        ).collect(Collectors.toList());
    }

    @GetMapping("{id}/followers")
    public List<String> findAllUsersFollowersById(@PathVariable Long id){
        return userService.findAllUsersFollowersById(id).stream().flatMap(
                userDto -> Stream.of(userDto.getUsername())
        ).collect(Collectors.toList());
    }

    @GetMapping("{id}/following/count")
    public int findAllUsersFollowingCountById(@PathVariable Long id){
        return userService.findAllUsersFollowingCountById(id);
    }

    @GetMapping("{id}/followers/count")
    public int findAllUsersFollowersCountById(@PathVariable Long id){
        return userService.findAllUsersFollowerCountsById(id);
    }

    @PostMapping("update")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @PostMapping("update/experience")
    public ExperienceUpdateDto updateUserExperience(@RequestBody ExperienceUpdateDto experienceUpdateDto){
        return userService.saveExperience(experienceUpdateDto);
    }

    @GetMapping(path = "{id}/games")
    public List<GameInfoDto> findGamesByCreatorId(@PathVariable Long id){

        List<GameInfoDto> result = userService.findGamesById(id);

        return result;
    }

    @PostMapping(path = "follow")
    public HttpResponseBody<String> follow(@RequestBody FollowingRequestDto followingRequest){

        HttpResponseBody<String> result = userService.follow(followingRequest);

        return result;
    }

    @PostMapping(path = "un-follow")
    public HttpResponseBody<String> unFollow(@RequestBody FollowingRequestDto followingRequest){

        HttpResponseBody<String> result = userService.unFollow(followingRequest);

        return result;
    }

    @GetMapping(path = "{id}/is-following/{followingId}")
    public Boolean isUserFollowing(@PathVariable Long id, @PathVariable Long followingId){

        Boolean result = userService.isFollowing(id, followingId);

        return result;
    }

    @GetMapping(path = "@/{username}")
    public UserDto isUserFollowing(@PathVariable String username){

        UserDto result = userService.findByUsername(username);

        return result;
    }

}
