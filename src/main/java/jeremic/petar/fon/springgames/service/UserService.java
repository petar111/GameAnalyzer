package jeremic.petar.fon.springgames.service;


import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.entity.User;

public interface UserService {
    UserDto findByUsername(String username);
}
