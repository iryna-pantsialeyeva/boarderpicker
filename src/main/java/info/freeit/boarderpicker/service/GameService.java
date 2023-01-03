package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;

import java.util.List;

public interface GameService {

    void saveGame(Game game);
    List<Game> getAllGames() throws GamesNotFoundException;
    Game getGameById(int id) throws GamesNotFoundException;
    void updateGame(Game game) throws GamesNotFoundException;
    void deleteGame(int id);
}
