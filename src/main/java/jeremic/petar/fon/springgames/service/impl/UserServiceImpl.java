package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.dto.ExperienceUpdateDto;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.dto.game.GameInfoDto;
import jeremic.petar.fon.springgames.entity.Rank;
import jeremic.petar.fon.springgames.entity.User;
import jeremic.petar.fon.springgames.exception.user.RankNotFoundException;
import jeremic.petar.fon.springgames.exception.user.UserNotFoundException;
import jeremic.petar.fon.springgames.mapper.GameMapper;
import jeremic.petar.fon.springgames.mapper.UserMapper;
import jeremic.petar.fon.springgames.repository.FollowingFollowerRepository;
import jeremic.petar.fon.springgames.repository.GameRepository;
import jeremic.petar.fon.springgames.repository.RankRepository;
import jeremic.petar.fon.springgames.repository.UserRepository;
import jeremic.petar.fon.springgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FollowingFollowerRepository followingFollowerRepository;
    private final RankRepository rankRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            FollowingFollowerRepository followingFollowerRepository, RankRepository rankRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, GameRepository gameRepository, GameMapper gameMapper) {
        this.userRepository = userRepository;
        this.followingFollowerRepository = followingFollowerRepository;
        this.rankRepository = rankRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }



    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usename " + username + " not found- User service.")));
    }

    @Override
    public List<UserDto> findAllUsersFollowingById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id not found.") //TODO: insert constant
        );

        return userMapper.toListDto(followingFollowerRepository.findAllByFollower(user).stream()
                .flatMap(followingFollower -> Stream.of(followingFollower.getFollowed()))
                .collect(Collectors.toList()));
    }

    @Override
    public List<UserDto> findAllUsersFollowersById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id not found.") //TODO: insert constant
        );
        return userMapper.toListDto(followingFollowerRepository.findAllByFollowed(user).stream()
                .flatMap(followingFollower -> Stream.of(followingFollower.getFollower()))
                .collect(Collectors.toList()));
    }

    @Override
    public int findAllUsersFollowingCountById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id not found.") //TODO: insert constant
        );
        return followingFollowerRepository.findAllByFollowed(user).size();
    }

    @Override
    public int findAllUsersFollowerCountsById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id not found.") //TODO: insert constant
        );
        return followingFollowerRepository.findAllByFollower(user).size();
    }

    @Override
    public UserDto save(UserDto userDto) {
        User userDb = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new UserNotFoundException("User with id not found.") //TODO: insert constant
        );
        User user = userMapper.toEntity(userDto);
        if(userDto.getPassword() == null){
            user.setPassword(userDb.getPassword());
        }else{
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        user.setExperience(userDb.getExperience());
        user.setNumberOfVerifiedGames(userDb.getNumberOfVerifiedGames());
        user.setRank(userDb.getRank());
        user.setAccountNonExpired( userDb.isAccountNonExpired() );
        user.setAccountNonLocked( userDb.isAccountNonLocked() );
        user.setCredentialsNonExpired( userDb.isCredentialsNonExpired() );
        user.setEnabled( userDb.isEnabled() );

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public ExperienceUpdateDto saveExperience(ExperienceUpdateDto experienceUpdateDto) {
        User user = userRepository.findById(experienceUpdateDto.getUser().getId()).orElseThrow(
                () -> new UserNotFoundException("User with id not found.") //TODO: insert constant
        );
        user.setExperience(user.getExperience() + experienceUpdateDto.getExperience());

        String promotionMessage = "";
        if(isUserReadyForPromotion(user)){
            user.setRank(resolveRankByUser(user));
            promotionMessage = "You have been promoted!!! Your new rank is " + user.getRank().getName() + ".";
        }


        User savedUser = userRepository.save(user);



        ExperienceUpdateDto experienceUpdateDtoResponse = new ExperienceUpdateDto();
        experienceUpdateDtoResponse.setUser(userMapper.toDto(savedUser));
        String experienceUpdateMessage = "Experience is successfully updated. You earned " + experienceUpdateDto.getExperience() + " exp.";
        experienceUpdateDtoResponse.setMessage(experienceUpdateMessage + " " + promotionMessage);

        return experienceUpdateDtoResponse;
    }

    @Override
    public List<GameInfoDto> findGamesById(Long id) {
        return gameMapper.toListGameInfoDto(gameRepository.findAllByCreator(userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User by id not found.")
        )));
    }

    private Rank resolveRankByUser(User user) {
        return rankRepository.findFirstByExperienceMaxGreaterThanAndExperienceMinLessThan(user.getExperience(), user.getExperience()).orElseThrow(
                () -> new RankNotFoundException("Rank between not found")
        );
    }


    private boolean isUserReadyForPromotion(User user) {
        return user.getExperience() > user.getRank().getExperienceMax();
    }

}
