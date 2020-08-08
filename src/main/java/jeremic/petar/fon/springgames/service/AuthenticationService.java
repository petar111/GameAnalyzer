package jeremic.petar.fon.springgames.service;

import jeremic.petar.fon.springgames.dto.LoginRequestDto;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.security.user.UserPrincipal;

public interface AuthenticationService {
    UserDto login(LoginRequestDto loginRequestDto);

    UserPrincipal getUserPrincipal(LoginRequestDto loginRequestDto);
}
