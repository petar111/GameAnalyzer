package jeremic.petar.fon.springgames.service;

import jeremic.petar.fon.springgames.dto.GameDTO;

public interface GameService {


    GameDTO findById(Long id);

}
