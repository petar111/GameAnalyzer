package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.dto.auth.LoginRequestDto;
import jeremic.petar.fon.springgames.dto.auth.RegisterRequestDto;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.entity.User;
import jeremic.petar.fon.springgames.exception.user.RankNotFoundException;
import jeremic.petar.fon.springgames.exception.user.UserAlreadyExists;
import jeremic.petar.fon.springgames.mapper.UserMapper;
import jeremic.petar.fon.springgames.repository.RankRepository;
import jeremic.petar.fon.springgames.repository.UserRepository;
import jeremic.petar.fon.springgames.security.user.UserPrincipal;
import jeremic.petar.fon.springgames.service.AuthenticationService;
import jeremic.petar.fon.springgames.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RankRepository rankRepository;


    public AuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            UserService userService, UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RankRepository rankRepository) {
        this.authenticationManager = authenticationManager;

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.rankRepository = rankRepository;
    }

    @Override
    public UserDto login(LoginRequestDto loginRequestDto) {
        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        UserDto result = userMapper.toDto(userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("")));
        return result;
    }

    @Override
    public UserPrincipal getUserPrincipal(String username) {
        return new UserPrincipal(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("")));
    }

    @Override
    public UserDto register(RegisterRequestDto registerRequestDto) {


        userRepository.findByUsername(registerRequestDto.getUsername()).ifPresent(
                (foundUser) -> {
                    throw new UserAlreadyExists("User with username " + foundUser.getUsername()
                    + " already exist.");
                }
        );
        userRepository.findByEmail(registerRequestDto.getEmail()).ifPresent(
                (foundUser) -> {
                    throw new UserAlreadyExists("User with email " + foundUser.getEmail()
                            + " already exist.");
                }
        );

        User user = prepareUserForRegistration(registerRequestDto);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    private User prepareUserForRegistration(RegisterRequestDto registerRequestDto) {
        User result = userMapper.toEntity(registerRequestDto);
        result.setExperience(0);
        result.setNumberOfVerifiedGames(0);
        result.setAccountNonLocked(true);
        result.setAccountNonExpired(true);
        result.setEnabled(true);
        result.setCredentialsNonExpired(true);
        result.setRank(rankRepository.findById(1L).orElseThrow(
                () -> new RankNotFoundException("Rank with id not found")
        ));
        result.setPassword(passwordEncoder.encode(result.getPassword()));

        return result;
    }


    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
