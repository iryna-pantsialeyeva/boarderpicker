package info.freeit.boarderpicker.service.impl;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.CustomNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public List<Game> getAllGames() throws CustomNullException {
        List<Game> games = (List) gameRepository.findAll();
        if (games.size() != 0) {
            return games;
        } else {
            throw new CustomNullException("There is no any games in DB!!!");
        }
    }

    public void deleteGame(Game game) {
        gameRepository.deleteById(game.getId());
    }
}
