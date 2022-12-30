package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.service.exception.CustomNullException;

import java.util.List;

public interface GameService {

    void saveGame(Game game);
    List<Game> getAllGames() throws CustomNullException;
    void deleteGame(Game game);
}
