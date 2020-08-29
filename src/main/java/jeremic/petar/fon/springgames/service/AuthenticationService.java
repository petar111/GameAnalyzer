package jeremic.petar.fon.springgames.service;

import jeremic.petar.fon.springgames.dto.auth.LoginRequestDto;
import jeremic.petar.fon.springgames.dto.auth.RegisterRequestDto;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.security.user.UserPrincipal;

public interface AuthenticationService {
    UserDto login(LoginRequestDto loginRequestDto);

    UserPrincipal getUserPrincipal(String username);

    UserDto register(RegisterRequestDto registerRequestDto);
}
