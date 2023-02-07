package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game saveGame(Game game) {
        if (gameRepository.findByName(game.getName()) != null) {
            throw new IllegalArgumentException(String.format("Game %s already exists",
                    game.getName()));
        } else {
            return gameRepository.save(game);
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
                .orElseThrow(() ->
                        new RuntimeException(String.format("The game with id %d does not exist!!!", id)));
    }

    @Override
    @Transactional
    public Game updateGame(int id, Game game) {
        Game foundGame = gameRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("The game with id %d does not exist!!!", id)));

        if (gameRepository.findByName(game.getName()) != null) {
            throw new IllegalArgumentException(String.format("Game %s already exists",
                    game.getName()));
        } else {
            foundGame.setName(game.getName());
            foundGame.setDescription(game.getDescription());
            foundGame.setPicPath(game.getPicPath());
            foundGame.setProducer(game.getProducer());
            foundGame.setCategories(game.getCategories());
            return foundGame;
        }
    }

    @Override
    public void deleteGame(int id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        } else {
            throw new RuntimeException(String.format("The game with id %d does not exist!!!", id));
        }
    }
}
