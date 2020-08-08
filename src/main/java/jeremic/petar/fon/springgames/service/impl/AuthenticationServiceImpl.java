package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.dto.LoginRequestDto;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.mapper.UserMapper;
import jeremic.petar.fon.springgames.repository.UserRepository;
import jeremic.petar.fon.springgames.security.user.UserPrincipal;
import jeremic.petar.fon.springgames.service.AuthenticationService;
import jeremic.petar.fon.springgames.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public AuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            UserService userService, UserRepository userRepository, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;

        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto login(LoginRequestDto loginRequestDto) {
        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        UserDto result = userMapper.toDto(userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("")));
        return result;
    }

    @Override
    public UserPrincipal getUserPrincipal(LoginRequestDto loginRequestDto) {
        return new UserPrincipal(userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("")));
    }


    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
