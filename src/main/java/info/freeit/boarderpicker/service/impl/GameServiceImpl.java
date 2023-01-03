package info.freeit.boarderpicker.service.impl;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
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

    public List<Game> getAllGames() throws GamesNotFoundException {
        List<Game> games = (List) gameRepository.findAll();
        if (games.size() != 0) {
            return games;
        } else {
            throw new GamesNotFoundException("There is no any games in DB!!!");
        }
    }

    public Game getGameById(int id) throws GamesNotFoundException {
        return gameRepository.findById(id).orElseThrow(() -> new GamesNotFoundException("There is no game with such id!!!"));
    }

    public void updateGame(Game game) throws GamesNotFoundException {
        Game foundGame = gameRepository.findById(game.getId()).orElseThrow(() -> new GamesNotFoundException("There is no game with such id!!!"));
        foundGame.setId(game.getId());
        foundGame.setGameName(game.getGameName());
        foundGame.setDescription(game.getDescription());
        foundGame.setPicPath(game.getPicPath());
        foundGame.setCategories(game.getCategories());
        foundGame.setProducer(game.getProducer());
        foundGame.setSales(game.getSales());
        gameRepository.save(foundGame);
    }

    public void deleteGame(int id) {
        gameRepository.deleteById(id);
    }
}
