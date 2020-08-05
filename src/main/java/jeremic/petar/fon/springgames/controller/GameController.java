package jeremic.petar.fon.springgames.controller;


import jeremic.petar.fon.springgames.dto.GameDTO;
import jeremic.petar.fon.springgames.dto.GameInfoDto;
import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.Strategy;
import jeremic.petar.fon.springgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
@CrossOrigin
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


}
