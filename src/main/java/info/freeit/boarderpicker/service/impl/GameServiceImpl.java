package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.repository.CategoryRepository;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.ProducerRepository;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void saveGame(Game game) {
        if(gameRepository.findByGameName(game.getGameName()) != null) {
            throw new IllegalArgumentException(String.format("Game %s already exists", game.getGameName()));
        } else {
            if (producerRepository.findByName(game.getProducer().getName()) == null) {
                producerRepository.save(game.getProducer());
            }
            game.setProducer(producerRepository.findByName(game.getProducer().getName()));

            for (Category category : game.getCategories()) {
                if (categoryRepository.findByName(category.getName()) == null) {
                    categoryRepository.save(category);
                }
                game.getCategories().add(categoryRepository.findByName(category.getName()));
            }

            gameRepository.save(game);
        }
    }

    @Override
    public List<Game> getAllGames() throws GamesNotFoundException {
        List<Game> games = gameRepository.findAll();
        if (games.size() != 0) {
            return games;
        } else {
            throw new GamesNotFoundException("There is no any games in DB!!!");
        }
    }

    @Override
    public Game getGameById(int id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("The game with id %d does not exist!!!", id)));
    }

    @Override
    public void updateGame(int id, Game game) {
        Game foundGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("The game with id %d does not exist!!!", id)));
        foundGame.setId(id);
        foundGame.setGameName(game.getGameName());
        foundGame.setDescription(game.getDescription());
        foundGame.setPicPath(game.getPicPath());
        foundGame.setCategories(game.getCategories());
        foundGame.setProducer(game.getProducer());
        foundGame.setSales(game.getSales());
        gameRepository.save(foundGame);
    }

    @Override
    public void deleteGame(int id) {
        if(gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        } else {
            throw new RuntimeException(String.format("The game with id %d does not exist!!!", id));
        }
    }
}
