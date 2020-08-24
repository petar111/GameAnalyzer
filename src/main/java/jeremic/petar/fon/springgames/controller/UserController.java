package jeremic.petar.fon.springgames.controller;

import jeremic.petar.fon.springgames.dto.ExperienceUpdateDto;
import jeremic.petar.fon.springgames.dto.HttpResponseBody;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService  userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}/following")
    public List<UserDto> findAllUsersFollowingById(@PathVariable Long id){
        return userService.findAllUsersFollowingById(id);
    }

    @GetMapping("{id}/followers")
    public List<UserDto> findAllUsersFollowersById(@PathVariable Long id){
        return userService.findAllUsersFollowersById(id);
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

}
