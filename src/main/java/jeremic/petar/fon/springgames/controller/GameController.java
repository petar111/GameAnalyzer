package jeremic.petar.fon.springgames.controller;


import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.dto.game.GameInfoDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionInfoDto;
import jeremic.petar.fon.springgames.entity.GameSession;
import jeremic.petar.fon.springgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {

    private final GameService gameService;


    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> findById(@PathVariable Long id){
        GameDTO result = gameService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<GameDTO> findByName(@RequestParam String name){
        GameDTO result = gameService.findByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameInfoDto>> findAll(){
        List<GameInfoDto> result = gameService.findAllInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "insert")
    public ResponseEntity<String> insertGame(@RequestBody GameDTO game){

        GameDTO savedGame = gameService.save(game);

        return new ResponseEntity<>("{\"message\":\"Game "+ savedGame.getName() +" is saved\"}", HttpStatus.OK);
    }

    @PostMapping(path = "game-session/save")
    public ResponseEntity<GameSessionDto> saveGameSession(@RequestBody GameSessionDto gameSession){

        GameSessionDto savedGameSession = gameService.saveGameSession(gameSession);

        return new ResponseEntity<>( savedGameSession, HttpStatus.OK);
    }

    @GetMapping(path = "game-session/get-by-creator")
    public ResponseEntity<List<GameSessionInfoDto>> getGameSessionByCreator(@RequestParam String username){

        List<GameSessionInfoDto> result = gameService.findAllGameSessionInfoByUsername(username);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "game-session/{id}")
    public ResponseEntity<GameSessionDto> getGameSessionById(@PathVariable Long id){

        GameSessionDto result = gameService.findGameSessionById(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
