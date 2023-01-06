package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;

import java.util.List;

public interface GameService {

    void saveGame(Game game);
    List<Game> getAllGames() throws GamesNotFoundException;
    Game getGameById(int id);
    void updateGame(int id, Game game);
    void deleteGame(int id);
}
