package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.dto.GameDTO;
import jeremic.petar.fon.springgames.entity.Strategy;
import jeremic.petar.fon.springgames.mapper.GameMapper;
import jeremic.petar.fon.springgames.repository.GameRepository;
import jeremic.petar.fon.springgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public GameDTO findById(Long id) {
        return gameMapper.toDto(gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Game with id %s doesnt exist.", id))));
    }
}
